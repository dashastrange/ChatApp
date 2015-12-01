package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ServerConnection {
    private String serverAddress;
    private String localNick;
    private Connection con;
    private Statement st;

    public ServerConnection() {
    }

    public ServerConnection(String address) {
        this(address, (String)null);
    }

    public ServerConnection(String address, String nick) {
        if(nick != null) {
            this.setLocalNick(nick);
        }

        if(address != null) {
            this.setServerAddress(address);
            this.connect();
        }

    }

    public void connect() {
        if(!this.isConnected()) {
            assert this.serverAddress != null && !this.serverAddress.trim().isEmpty();

            try {
                this.con = DriverManager.getConnection(this.serverAddress, "guest", "guest");
                this.st = this.con.createStatement();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }

        }
    }

    public void disconnect() {
        if(this.st != null) {
            try {
                this.st.close();
            } catch (SQLException var3) {
                var3.printStackTrace();
            }

            this.st = null;
        }

        if(this.con != null) {
            try {
                this.con.close();
            } catch (SQLException var2) {
                var2.printStackTrace();
            }

            this.con = null;
        }

    }

    public boolean isConnected() {
        return this.st != null;
    }

    public String getServerAddress() {
        return this.serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        if(!serverAddress.equals(this.serverAddress)) {
            boolean connected = this.isConnected();
            if(connected) {
                this.goOffline();
                this.disconnect();
            }

            this.serverAddress = serverAddress;
            if(connected) {
                this.connect();
            }

        }
    }

    public String getLocalNick() {
        return this.localNick;
    }

    public void setLocalNick(String newNick) {
        assert newNick != null;

        newNick = safe(newNick);
        if(!newNick.equals(this.localNick)) {
            boolean online = false;
            if(this.isConnected()) {
                online = this.isNickOnline(this.localNick);
                if(online) {
                    this.goOffline();
                }
            }

            this.localNick = newNick;
            if(online) {
                this.goOnline();
            }

        }
    }

    public void goOnline() {
        this.goOnline(28411);
    }

    public void goOnline(int port) {
        assert this.localNick != null;

        assert this.isConnected();

        String q = "INSERT INTO user (nick, ip, online, port) values (\'" + this.localNick + "\', SUBSTRING_INDEX(USER(),\'@\',-1), 1,\'" + port + "\');";

        try {
            this.st.executeUpdate(q);
        } catch (SQLException var9) {
            boolean nick_collision = true;

            try {
                if(this.getIpForNick(this.localNick) != null) {
                    q = "UPDATE user set ip=SUBSTRING_INDEX(USER(),\'@\',-1), online=1, port=" + port + " WHERE nick=\'" + this.localNick + "\';";
                    nick_collision = true;
                } else {
                    q = "UPDATE user set nick=\'" + this.localNick + "\', online=1 WHERE ip=SUBSTRING_INDEX(USER(),\'@\',-1) AND port=" + port + ";";
                    nick_collision = false;
                }

                this.st.executeUpdate(q);
            } catch (SQLException var8) {
                try {
                    if(nick_collision) {
                        this.st.execute("DELETE FROM user WHERE ip=SUBSTRING_INDEX(USER(),\'@\',-1) AND port=" + port + ";");
                    } else {
                        this.st.execute("DELETE FROM user WHERE nick=\'" + this.localNick + "\';");
                    }

                    this.st.execute(q);
                } catch (SQLException var7) {
                    var8.printStackTrace();
                }
            }
        }

    }

    public void goOffline() {
        assert this.localNick != null;

        assert this.isConnected();

        try {
            this.st.execute("INSERT INTO user (nick, ip, online) values (\'" + this.localNick + "\', SUBSTRING_INDEX(USER(),\'@\',-1), 0) ON DUPLICATE KEY UPDATE ip=VALUES(ip), online=VALUES(online);");
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }

    public String getIpForNick(String nick) {
        assert this.isConnected();

        assert nick != null;

        nick = safe(nick);

        try {
            ResultSet rs = this.st.executeQuery("SELECT ip FROM user WHERE nick=\'" + nick + "\'");
            return !rs.next()?null:rs.getString(1);
        } catch (SQLException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public int getPortForNick(String nick) {
        assert this.isConnected();

        assert nick != null;

        nick = safe(nick);

        try {
            ResultSet rs = this.st.executeQuery("SELECT port FROM user WHERE nick=\'" + nick + "\'");
            return !rs.next()?0:rs.getInt(1);
        } catch (SQLException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    public boolean isNickOnline(String nick) {
        if(nick == null) {
            return false;
        } else {
            assert this.isConnected();

            nick = safe(nick);

            try {
                ResultSet rs = this.st.executeQuery("SELECT online FROM user WHERE nick=\'" + nick + "\'");
                return !rs.next()?false:rs.getBoolean(1);
            } catch (SQLException var4) {
                var4.printStackTrace();
                return false;
            }
        }
    }

    public String[] getAllNicks() {
        assert this.isConnected();

        ArrayList res = new ArrayList();

        try {
            ResultSet rs = this.st.executeQuery("SELECT nick FROM user;");

            while(rs.next()) {
                String e = rs.getString(1);
                res.add(e);
            }
        } catch (SQLException var4) {
            var4.printStackTrace();
        }

        return (String[])res.toArray(new String[0]);
    }

    private static String safe(String s) {
        return s.replaceAll("[\'\";]", "").replaceAll("\\s", "");
    }

    public static void main(String[] args) {
        ServerConnection c = new ServerConnection();
        c.setServerAddress("jdbc:mysql://files.litvinov.in.ua/chatapp_server");
        c.connect();

        assert c.isConnected();

        c.setLocalNick("ArtemSiminenko");
        System.out.println("Before: " + c.isNickOnline("ArtemSiminenko"));
        c.goOnline();
        System.out.println("After: " + c.isNickOnline("ArtemSiminenko"));
        //c.goOffline();
        String[] s=c.getAllNicks();
        for (int i=1;i<s.length;i++){
        	System.out.println(i+". "+s[i]+" "+c.isNickOnline(s[i]));
        }
        c.goOffline();
    }
}