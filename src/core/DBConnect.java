package core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBConnect
{

    private static Connection conn;
    private static Statement statmt;
    private static ResultSet resSet;
    private static Window window;

    DBConnect() throws SQLException, ClassNotFoundException
    {
    }

    static void Conn() throws ClassNotFoundException, SQLException
    {
        conn = null;
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:gmadb.sqlite");
        statmt = conn.createStatement();
        System.out.println("База Подключена!");
    }

//    public static void CreateDB() throws SQLException
//    {
//        statmt.execute("CREATE TABLE if not exists 'users' ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' text, 'phone' INT);");
//        System.out.println("Таблица создана или уже существует.");
//    }
//
//    public static void WriteDB() throws SQLException
//    {
//        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Petya', 125453); ");
//        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
//        statmt.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");
//        System.out.println("Таблица заполнена");
//    }

    static void ReadDB() throws SQLException
    {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables("gmadb", "", "%", (String[])null);

        while(rs.next()) {
            System.out.println(rs.getString(3));
        }

        resSet = statmt.executeQuery("SELECT * FROM Character");
        int id = 0;
        String name = "";
        while(resSet.next()) {
            id = resSet.getInt("character_id");
            name = resSet.getString("character_name");
            System.out.println("ID = " + id);
            System.out.println("name = " + name);
        }

        //(window.getLabelId()).setText(String.valueOf(id));
        //(window.getLabelName()).setText(name);

        rs.close();
        resSet.close();
        System.out.println("Таблица выведена");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        window = new Window();
    }
//==============================GET=======================================
    static String[] getAllCharacter() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Character");
        resSet.next();
        String[] names = new String[resSet.getInt(1)];

        ArrayList<Integer>id = new ArrayList<>();

        resSet = statmt.executeQuery("SELECT character_name, character_id FROM Character");
        for (int i = 0; resSet.next(); i++)
        {
            names[i] = resSet.getString("character_name");
            id.add(resSet.getInt("character_id"));

        }
        Window.allCharacterId = id;
        resSet.close();
        return names;
    }

    static String[][] getAllAdvantage() throws SQLException {

        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Advantage");
        resSet.next();
        String[][] data = new String[4][resSet.getInt(1)];
        resSet = statmt.executeQuery("SELECT advantage_name,cost,max_level,description FROM Advantage");
        for (int i = 0; resSet.next(); i++)
        {
            data[0][i] = resSet.getString(1);
            data[1][i] = resSet.getString(2);
            data[2][i] = resSet.getString(3);
            data[3][i] = resSet.getString(4);
        }
        resSet.close();
        return data;
    }

    static String[][] getAllDisadvantage() throws SQLException {

        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Disadvantage");
        resSet.next();
        String[][] data = new String[4][resSet.getInt(1)];
        resSet = statmt.executeQuery("SELECT disadvantage_name,cost,max_level,description FROM Disadvantage");
        for (int i = 0; resSet.next(); i++)
        {
            data[0][i] = resSet.getString(1);
            data[1][i] = resSet.getString(2);
            data[2][i] = resSet.getString(3);
            data[3][i] = resSet.getString(4);
        }
        resSet.close();
        return data;
    }

    static String[][] getAllQuirk() throws SQLException {

        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Quirk");
        resSet.next();
        String[][] data = new String[2][resSet.getInt(1)];
        resSet = statmt.executeQuery("SELECT quirk_name,description FROM Quirk");
        for (int i = 0; resSet.next(); i++)
        {
            data[0][i] = resSet.getString(1);
            data[1][i] = resSet.getString(2);
        }
        resSet.close();
        return data;
    }

    static String[][] getAllSkill() throws SQLException {

        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Skill");
        resSet.next();
        String[][] data = new String[5][resSet.getInt(1)];
        resSet = statmt.executeQuery("SELECT skill_name, type, difficulty, relative_level,description FROM Skill");
        for (int i = 0; resSet.next(); i++)
        {
            data[0][i] = resSet.getString(1);
            data[1][i] = resSet.getString(2);
            data[2][i] = resSet.getString(3);
            data[3][i] = resSet.getString(4);
            data[4][i] = resSet.getString(5);
        }
        resSet.close();
        return data;
    }

    static float[] getCharacterStats(int characterId) throws SQLException
    {
        float[] data = new float[10];
        resSet = statmt.executeQuery("SELECT ST,DX,IQ,HT,HP,Will,Pers,FP,basic_speed,basic_move FROM Character WHERE character_id=" + characterId );
        if(resSet.next())
            for (int i = 0; i < data.length ;i++)
            {
                data[i] = resSet.getFloat(i + 1);
            }
        resSet.close();
        return data;
    }

    static String[] getCharacterInfo(int characterId) throws SQLException
    {
        String[] data = new String[8];
        resSet = statmt.executeQuery("SELECT character_name,age,initial_points,remaining_points,description,height,weight,race FROM Character WHERE character_id=" + characterId);
        if(resSet.next())
            for (int i = 0; i < data.length ;i++)
            {
                data[i] = resSet.getString(i + 1);
            }
        resSet.close();
        return data;
    }

    static Object[][] getCharacterAdvantage(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT advantage_name, cost, Character_advantages.level FROM Advantage, Character_advantages WHERE Character_advantages.character_id=" + characterId + " AND Advantage.advantage_id=Character_advantages.advantage_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getInt( 2), resSet.getInt( 3)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][3]);
    }

    static Object[][] getCharacterDisadvantage(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT disadvantage_name, cost, Character_disadvantages.level FROM Disadvantage, Character_disadvantages WHERE Character_disadvantages.character_id=" + characterId + " AND Disadvantage.disadvantage_id=Character_disadvantages.disadvantage_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getInt( 2), resSet.getInt( 3)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][3]);
    }

    static Object[][] getCharacterQuirk(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT Quirk_name, Quirk.description FROM Quirk, Character_quirks WHERE Character_Quirks.character_id=" + characterId + " AND Quirk.quirk_id=Character_quirks.quirk_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getString( 2)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][2]);
    }

    static Object[][] getCharacterSkills(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT skill_name, type, difficulty, relative_level, Character_skills.level, Character_skills.cost FROM Skill, Character_skills WHERE Character_skills.character_id=" + characterId + " AND Skill.skill_id=Character_skills.skill_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getString( 2), resSet.getString( 3), resSet.getString(4), resSet.getInt(5), resSet.getInt(6)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][6]);
    }

    static String getAdvantageOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Advantage WHERE advantage_name='"
                + String.valueOf(valueAt) + "'");
        resSet.next();
        String data = resSet.getString(1);

        resSet.close();
        return data;
    }

    static String getDisadvantageOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Disadvantage WHERE disadvantage_name='"
                + String.valueOf(valueAt) + "'");
        resSet.next();
        String data = resSet.getString(1);

        resSet.close();
        return data;
    }

    static String getQuirkOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Quirk WHERE quirk_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "Эта причуда ещё не сохранена в словаре";

        resSet.close();
        return data;
    }

    static String getSkillOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Skill WHERE skill_name='"
                + String.valueOf(valueAt) + "'");
        String data = resSet.getString(1);

        resSet.close();
        return data;
    }

    static String getThrDamageOnST(int ST) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT Thr FROM Base_ST_damage WHERE ST=" + ST);
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "Err";

        resSet.close();
        return data;
    }
    static String getSwDamageOnST(int ST) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT Sw FROM Base_ST_damage WHERE ST=" + ST);
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "Err";

        resSet.close();
        return data;
    }

//==============================GET=======================================
//==============================SAVE=======================================

    static void saveStats(int[] stats, float basicSpreed,  int characterId) throws SQLException
    {
         statmt.executeUpdate("UPDATE Character SET ST=" + stats[0]
                 + ",DX=" + stats[1]
                 + ",IQ=" + stats[2]
                 + ",HT=" + stats[3]
                 + ",HP=" + stats[4]
                 + ",Will=" + stats[5]
                 + ",Pers=" + stats[6]
                 + ",FP=" + stats[7]
                 + ",basic_speed=" + basicSpreed
                 + ",basic_move=" + stats[8]
                 + " WHERE character_id=" + characterId);
        System.out.println("Характеристики " + characterId + " обновлены");
    }

    static void saveInfo(String[] info, int characterId) throws SQLException
    {
         statmt.executeUpdate("UPDATE Character SET character_name='" + info[0] + "'"
                + ",age=" + info[1]
                + ",initial_points=" + info[2]
                + ",remaining_points=" + info[3]
                + ",description='" + info[4] + "'"
                + ",height=" + info[5]
                + ",weight=" + info[6]
                + ",race='" + info[7] + "'"
                + " WHERE character_id=" + characterId);
        System.out.println("Информация " + characterId + " обновлена");
    }

    static void saveAdvantage(String[] name, int[] level, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_advantages WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT advantage_id FROM Advantage WHERE advantage_name='" + name[i] + "'");
            statmt.executeUpdate("INSERT INTO Character_advantages(advantage_id, character_id, level) VALUES ("
                    + resSet.getInt(1)+ ","
                    + characterId + ","
                    + level[i] + ")");
        }

        resSet.close();
        System.out.println("Преимущества  " + characterId + " обновлены");
    }

    static void saveDisadvantage(String[] name, int[] level, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_disadvantages WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT disadvantage_id FROM Disadvantage WHERE disadvantage_name='" + name[i] + "'");
            statmt.executeUpdate("INSERT INTO Character_disadvantages(disadvantage_id, character_id, level) VALUES ("
                    + resSet.getInt(1)+ ","
                    + characterId + ","
                    + level[i] + ")");
        }

        resSet.close();
        System.out.println("Недостатки  " + characterId + " обновлены");
    }

    static void saveQuirk(String[] name, String[] description, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_quirks WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT quirk_id FROM Quirk WHERE quirk_name='" + name[i] + "'");
            if (resSet.next())
                statmt.executeUpdate("INSERT INTO Character_quirks(quirk_id, character_id) VALUES ("
                    + resSet.getInt(1) + ","
                    + characterId + ")");
            else
                statmt.executeUpdate("INSERT INTO Quirk(quirk_name, description) VALUES ('"
                        + name[i] + "','"
                        + description[i] + "')");
        }

        resSet.close();
        System.out.println("Причуды  " + characterId + " обновлены");
    }

    static void saveSkill(String[] name, int[] level, int[] cost, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_skills WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT skill_id FROM Skill WHERE skill_name='" + name[i] + "'");
            statmt.executeUpdate("INSERT INTO Character_skills(skill_id, character_id, level, cost) VALUES ("
                    + resSet.getInt(1)+ ","
                    + characterId + ","
                    + level[i]  + ","
                    + cost[i] + ")");
        }

        resSet.close();
        System.out.println("Навыки  " + characterId + " обновлены");
    }

//==============================SAVE=======================================
//==============================CREATE=====================================
    static void createCharacter(int characterId) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Character (character_id,character_name,age,initial_points,remaining_points,description,height,weight,race,ST,DX,IQ,HT,HP,Will,Pers,FP,basic_speed,basic_move) " +
                "VALUES (" + characterId + ",'name',0,100,100,'description',1,1,'race',10,10,10,10,10,10,10,10,5,5)");
        System.out.println("Персонаж " + characterId + " создан");
    }


//==============================CREATE=====================================
//==============================DELETE=====================================
    static void deleteCharacter(int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character  WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_advantages WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_disadvantages WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_quirks WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_skills WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_armor WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_hand_weapon WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Character_ranged_weapon WHERE character_id=" + characterId);
        statmt.executeUpdate("DELETE FROM Inventory WHERE character_id=" + characterId);
        System.out.println("Персонаж " + characterId + " удалён");
    }


//==============================DELETE=====================================

}
