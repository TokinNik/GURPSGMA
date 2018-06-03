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

    static String[][] getAllAdvantage() throws SQLException
    {
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

    static String[][] getAllDisadvantage() throws SQLException
    {
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

    static String[][] getAllQuirk() throws SQLException
    {
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

    static String[][] getAllSkill() throws SQLException
    {
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

     static String[][] getAllArmor() throws SQLException
     {
         resSet = statmt.executeQuery("SELECT COUNT(*) FROM Armor");
         resSet.next();
         String[][] data = new String[6][resSet.getInt(1)];
         resSet = statmt.executeQuery("SELECT armor_name, zones, DR, weight, cost, description FROM Armor");
         for (int i = 0; resSet.next(); i++)
         {
             data[0][i] = resSet.getString(1);
             data[1][i] = resSet.getString(2);
             data[2][i] = resSet.getString(3);
             data[3][i] = resSet.getString(4);
             data[4][i] = resSet.getString(5);
             data[5][i] = resSet.getString(6);
         }
         resSet.close();
         return data;
     }

    static String[][] getAllHandWeapon() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Hand_weapon");
        resSet.next();
        String[][] data = new String[8][resSet.getInt(1)];
        resSet = statmt.executeQuery("SELECT hand_weapon_name, damage, damage_type, min_ST, KD, cost, weight, description FROM Hand_weapon");
        for (int i = 0; resSet.next(); i++)
        {
            data[0][i] = resSet.getString(1);
            data[1][i] = resSet.getString(2);
            data[2][i] = resSet.getString(3);
            data[3][i] = resSet.getString(4);
            data[4][i] = resSet.getString(5);
            data[5][i] = resSet.getString(6);
            data[6][i] = resSet.getString(7);
            data[7][i] = resSet.getString(8);
        }
        resSet.close();
        return data;
    }

    static String[][] getAllRangedWeapon() throws SQLException
    {
        resSet = statmt.executeQuery("SELECT COUNT(*) FROM Ranged_weapon");
        resSet.next();
        String[][] data = new String[14][resSet.getInt(1)];
        resSet = statmt.executeQuery("SELECT ranged_weapon_name, damage, damage_type, SS, Acc, range, max_range, RoF, shots, min_ST, Rcl, cost, weight, description FROM Ranged_weapon");
        for (int i = 0; resSet.next(); i++)
        {
            data[0][i] = resSet.getString(1);
            data[1][i] = resSet.getString(2);
            data[2][i] = resSet.getString(3);
            data[3][i] = resSet.getString(4);
            data[4][i] = resSet.getString(5);
            data[5][i] = resSet.getString(6);
            data[6][i] = resSet.getString(7);
            data[7][i] = resSet.getString(8);
            data[8][i] = resSet.getString(9);
            data[9][i] = resSet.getString(10);
            data[10][i] = resSet.getString(11);
            data[11][i] = resSet.getString(12);
            data[12][i] = resSet.getString(13);
            data[13][i] = resSet.getString(14);
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

    static Object[][] getCharacterArmor(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT armor_name, zones, DR, weight, cost FROM Armor, Character_armor WHERE Character_armor.character_id=" + characterId + " AND Armor.armor_id=Character_armor.armor_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getString( 2), resSet.getInt( 3), resSet.getInt(4), resSet.getInt(5)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][5]);
    }

     static Object[][] getCharacterInventory(int characterId)  throws SQLException
     {
         resSet = statmt.executeQuery("SELECT item_name, Amount, weight, cost FROM Inventory WHERE character_id=" + characterId);
         ArrayList<Object[]> arr = new ArrayList<>();
         for (int i = 0; resSet.next(); i++)
         {
             arr.add(i, new Object[]{resSet.getString(1), resSet.getFloat( 2), resSet.getFloat( 3), resSet.getFloat(4)});
         }
         resSet.close();
         return arr.toArray(new Object[arr.size()][4]);
     }

    static Object[][] getCharacterHandWeapon(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT hand_weapon_name, damage, damage_type, min_ST, KD, weight, cost FROM Hand_weapon, Character_hand_weapon WHERE Character_hand_weapon.character_id=" + characterId + " AND Hand_weapon.hand_weapon_id=Character_hand_weapon.hand_weapon_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getString( 2), resSet.getString( 3), resSet.getInt(4), resSet.getInt(5), resSet.getFloat(6), resSet.getFloat(7)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][7]);
    }

    static Object[][] getCharacterRangedWeapon(int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT ranged_weapon_name, damage, damage_type, SS, Acc, range, max_range, RoF, shots, min_ST, Rcl, weight, cost FROM Ranged_weapon, Character_ranged_weapon WHERE Character_ranged_weapon.character_id=" + characterId + " AND Ranged_weapon.ranged_weapon_id=Character_ranged_weapon.ranged_weapon_id");
        ArrayList<Object[]> arr = new ArrayList<>();
        for (int i = 0; resSet.next(); i++)
        {
            arr.add(i, new Object[]{resSet.getString(1), resSet.getString( 2), resSet.getString( 3), resSet.getInt(4), resSet.getInt(5), resSet.getInt(6), resSet.getInt(7), resSet.getInt(8), resSet.getInt(9), resSet.getInt(10), resSet.getInt(11), resSet.getFloat(12), resSet.getFloat(13)});
        }
        resSet.close();
        return arr.toArray(new Object[arr.size()][13]);
    }

    static String getAdvantageOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Advantage WHERE advantage_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

        resSet.close();
        return data;
    }

    static String getDisadvantageOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Disadvantage WHERE disadvantage_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

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
            data = "null";

        resSet.close();
        return data;
    }

    static String getSkillOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Skill WHERE skill_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

        resSet.close();
        return data;
    }

    static String getArmorOnName(Object valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Armor WHERE armor_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

        resSet.close();
        return data;
    }

    static String getItemOnName(String valueAt, int characterId) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Inventory WHERE item_name='"
                + String.valueOf(valueAt) + "' AND character_id=" + characterId);
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

        resSet.close();
        return data;
    }

    static String getHandWeaponOnName(String valueAt) throws  SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Hand_weapon WHERE hand_weapon_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

        resSet.close();
        return data;
    }

    static String getRangedWeaponOnName(String valueAt) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT description FROM Ranged_weapon WHERE ranged_weapon_name='"
                + String.valueOf(valueAt) + "'");
        String data;
        if (resSet.next())
            data = resSet.getString(1);
        else
            data = "null";

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

    static void saveQuirk(String[] name, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_quirks WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT quirk_id FROM Quirk WHERE quirk_name='" + name[i] + "'");
                statmt.executeUpdate("INSERT INTO Character_quirks(quirk_id, character_id) VALUES ("
                    + resSet.getInt(1) + ","
                    + characterId + ")");

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

    static void saveArmor(String[] name, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_armor WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT armor_id FROM Armor WHERE armor_name='" + name[i] + "'");
            statmt.executeUpdate("INSERT INTO Character_armor(armor_id, character_id) VALUES ("
                    + resSet.getInt(1)+ ","
                    + characterId + ")");
        }

        resSet.close();
        System.out.println("Броня  " + characterId + " обновлена");
    }

    static void saveInventory(String[] name, float[] amount, float[] weight, float[] cost, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Inventory WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            statmt.executeUpdate("INSERT INTO Inventory(character_id, item_name, cost, weight, amount) VALUES ("
                    + characterId + ",'"
                    + name[i] + "',"
                    + cost[i]  + ","
                    + weight[i] + ","
                    + amount[i] + ")");
        }

        resSet.close();
        System.out.println("Инвентарь  " + characterId + " обновлены");
    }

    static void saveHandWeapon(String[] name, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_hand_weapon WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT hand_weapon_id FROM Hand_weapon WHERE hand_weapon_name='" + name[i] + "'");
            statmt.executeUpdate("INSERT INTO Character_hand_weapon(hand_weapon_id, character_id) VALUES ("
                    + resSet.getInt(1)+ ","
                    + characterId + ")");
        }

        resSet.close();
        System.out.println("Оружие ближнего боя  " + characterId + " обновлено");
    }


    static void saveRangedWeapon(String[] name, int characterId) throws SQLException
    {
        statmt.executeUpdate("DELETE FROM Character_ranged_weapon WHERE character_id=" + characterId);
        for (int i = 0; i < name.length; i++)
        {
            resSet = statmt.executeQuery("SELECT ranged_weapon_id FROM Ranged_weapon WHERE ranged_weapon_name='" + name[i] + "'");
            statmt.executeUpdate("INSERT INTO Character_ranged_weapon(ranged_weapon_id, character_id) VALUES ("
                    + resSet.getInt(1)+ ","
                    + characterId + ")");
        }

        resSet.close();
        System.out.println("Оружие дальнего боя  " + characterId + " обновлено");
    }

//==============================SAVE=======================================
//==============================ADD========================================
    static void addNewAdvantage(String name, int cost, int maxLevel, String description) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Advantage(advantage_name, cost, max_level, description) VALUES ('"
                + name + "',"
                + cost + ","
                + maxLevel + ",'"
                + description + "')");
    }

    static void addNewDisadvantage(String name, int cost, int maxLevel, String description) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Disadvantage(disadvantage_name, cost, max_level, description) VALUES ('"
                + name + "',"
                + cost + ","
                + maxLevel + ",'"
                + description + "')");
    }

    static void addNewQuirk(String name, String description) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Quirk(quirk_name, description) VALUES ('"
                + name + "','"
                + description + "')");
    }

    static void addNewSkill (String name, String type, String difficulty, String relativeLevel, String description) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Skill(skill_name, type, difficulty, relative_level, description) VALUES ('"
                + name + "','"
                + type + "','"
                + difficulty + "','"
                + relativeLevel + "','"
                + description + "')");
    }

    static void addNewArmor (String name, String DR, String zones, String cost, String weight, String description) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Armor(armor_name, DR, zones, cost, weight, description) VALUES ('"
                + name + "',"
                + DR + ",'"
                + zones + "',"
                + cost + ","
                + weight + ",'"
                + description + "')");
    }

    static void addNewHandWeapon(String name, String damage, String damageType, String minST, String KD, String weight, String cost, String description) throws SQLException {
        statmt.executeUpdate("INSERT INTO Hand_weapon(hand_weapon_name, damage, damage_type, min_ST, KD, weight, cost, description) VALUES ('"
                + name + "','"
                + damage + "','"
                + damageType + "',"
                + minST + ","
                + KD + ","
                + cost + ","
                + weight + ",'"
                + description + "')");
    }

    static void addNewRangedWeapon(String name, String damage, String damageType, String SS, String Acc, String range, String maxRange, String RoF, String shots, String minST, String Rcl, String weight, String cost, String description) throws SQLException
    {
        statmt.executeUpdate("INSERT INTO Ranged_weapon(ranged_weapon_name, damage, damage_type, SS, Acc, range, max_range, RoF, shots, min_ST, Rcl, cost, weight, description) VALUES ('"
                + name + "','"
                + damage + "','"
                + damageType + "',"
                + SS + ","
                + Acc + ","
                + range + ","
                + maxRange + ","
                + RoF + ","
                + shots + ","
                + minST + ","
                + Rcl + ","
                + cost + ","
                + weight + ",'"
                + description + "')");
    }

//==============================ADD========================================
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

    static void deleteAdvantageOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT advantage_id FROM Advantage WHERE advantage_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_advantages WHERE advantage_id=" + id);
        statmt.executeUpdate("DELETE FROM Advantage WHERE advantage_id=" + id);

        System.out.println("Преимущество " + name + " удалено");
    }

    static void deleteDisadvantageOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT disadvantage_id FROM Disadvantage WHERE disadvantage_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_disadvantages WHERE disadvantage_id=" + id);
        statmt.executeUpdate("DELETE FROM Disadvantage WHERE disadvantage_id=" + id);

        System.out.println("Недостаток " + name + " удалён");
    }

    static void deleteQuirkOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT quirk_id FROM Quirk WHERE quirk_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_quirks WHERE quirk_id=" + id);
        statmt.executeUpdate("DELETE FROM Quirk WHERE quirk_id=" + id);

        System.out.println("Причуда " + name + " удалена");
    }

    static void deleteArmorOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT armor_id FROM Armor WHERE armor_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_armor WHERE armor_id=" + id);
        statmt.executeUpdate("DELETE FROM Armor  WHERE armor_id=" + id);

        System.out.println("Броня " + name + " удалена");
    }

    static void deleteSkillOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT skill_id FROM Skill WHERE skill_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_skills WHERE skill_id=" + id);
        statmt.executeUpdate("DELETE FROM Skill WHERE skill_id=" + id);

        System.out.println("Навык " + name + " удалён");
    }

    static void deleteHandWeaponOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT hand_weapon_id FROM Hand_weapon WHERE hand_weapon_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_hand_weapon WHERE hand_weapon_id=" + id);
        statmt.executeUpdate("DELETE FROM Hand_weapon WHERE hand_weapon_id=" + id);

        System.out.println("Оружие ближнего боя " + name + " удалено");
    }

    static void deleteRangedWeaponOnName(String name) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT ranged_weapon_id FROM Ranged_weapon WHERE ranged_weapon_name='" + name + "'");
        int id = resSet.getInt(1);
        statmt.executeUpdate("DELETE FROM Character_ranged_weapon WHERE ranged_weapon_id=" + id);
        statmt.executeUpdate("DELETE FROM Ranged_weapon WHERE ranged_weapon_id=" + id);

        System.out.println("Оружие дальнего боя " + name + " удалено");
    }


//==============================DELETE=====================================
//==============================UPDATE=====================================
    static void updateAdvantage(String oldName, String name, int cost, int maxLevel, String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT advantage_id FROM Advantage WHERE advantage_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Advantage SET " +
                "advantage_name='" + name +
                "',cost=" + cost +
                ",max_level=" + maxLevel +
                ",description='" + description +
                "' WHERE advantage_id=" +resSet.getInt(1));
        System.out.println("Преимущество " + oldName + " обновлено");
    }

    static void updateDisadvantage(String oldName, String name, int cost, int maxLevel, String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT disadvantage_id FROM Disadvantage WHERE disadvantage_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Disadvantage SET " +
                "disadvantage_name='" + name +
                "',cost=" + cost +
                ",max_level=" + maxLevel +
                ",description='" + description +
                "' WHERE disadvantage_id=" +resSet.getInt(1));
        System.out.println("Недостаток " + oldName + " обновлён");
    }

    static void updateQuirk(String oldName, String name,String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT quirk_id FROM Quirk WHERE quirk_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Quirk SET " +
                "quirk_name='" + name +
                "',description='" + description +
                "' WHERE quirk_id=" +resSet.getInt(1));
        System.out.println("Причуда " + oldName + " обновлена");
    }


    static void updateArmor (String oldName, String name, String DR, String zones, String cost, String weight, String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT armor_id FROM Armor WHERE armor_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Armor SET " +
                        "armor_name='" + name +
                        "',DR=" + DR +
                        ",zones='" + zones +
                        "',cost=" + cost +
                        ",weight=" + weight +
                        ",description='" + description +
                        "' WHERE armor_id=" +resSet.getInt(1));
        System.out.println("Броня " + oldName + " обновлена");
    }

    static void updateSkill (String oldName, String name, String type, String difficulty, String relativeLevel, String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT skill_id FROM Skill WHERE skill_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Skill SET " +
                "skill_name='" + name +
                "',type='" + type +
                "',difficulty='" + difficulty +
                "',relative_level='" + relativeLevel +
                "',description='" + description +
                "' WHERE skill_id=" +resSet.getInt(1));
        System.out.println("Навык " + oldName + " обновлён");
    }

    static void updateHandWeapon (String oldName, String name, String damage, String damageType, String minST, String KD, String weight, String cost, String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT hand_weapon_id FROM Hand_weapon WHERE hand_weapon_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Hand_weapon SET " +
                "hand_weapon_name='" + name +
                "',damage='" + damage +
                "',damage_type='" + damageType +
                "',min_ST=" + minST +
                ",KD=" + KD +
                ",weight=" + weight +
                ",cost=" + cost +
                ",description='" + description +
                "' WHERE hand_weapon_id=" +resSet.getInt(1));
        System.out.println("Оружие ближнего боя " + oldName + " обновлено");
    }

    static void updateRangedWeapon (String oldName, String name, String damage, String damageType, String SS, String Acc, String range, String maxRange, String RoF, String shots, String minST, String Rcl, String weight, String cost, String description) throws SQLException
    {
        resSet = statmt.executeQuery("SELECT ranged_weapon_id FROM Ranged_weapon WHERE ranged_weapon_name='" + oldName + "'");
        statmt.executeUpdate("UPDATE Ranged_weapon SET " +
                "ranged_weapon_name='" + name +
                "',damage='" + damage +
                "',damage_type='" + damageType +
                "',SS=" + SS +
                ",Acc=" + Acc +
                ",range=" + range +
                ",max_range=" + maxRange +
                ",Rof=" + RoF +
                ",shots=" + shots +
                ",min_ST=" + minST +
                ",Rcl=" + Rcl +
                ",weight=" + weight +
                ",cost=" + cost +
                ",description='" + description +
                "' WHERE ranged_weapon_id=" +resSet.getInt(1));
        System.out.println("Оружие дальнего боя " + oldName + " обновлено");
    }

//==============================UPDATE=====================================

}
