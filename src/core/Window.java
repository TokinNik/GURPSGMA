package core;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


class Window extends JFrame
{
    static BaseStatPanel basePanel;
    static InfoPanel infoPanel;
    static PerksPanel perksPanel;
    static SkillsPanel skillsPanel;
    static ArrayList<Integer> allCharacterId;
    static int characterId;
    static boolean isChanged;
    private JList<String> characterList;

    Window () throws SQLException, ClassNotFoundException
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        isChanged = false;
        DBConnect.Conn();
        setTitle("GURPS GMA");
        setSize(Resources.width, Resources.height);
        setLocation(0, 50);

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.fill   = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 1;
        c.weighty = 0.0;
//------------------mainPanel--------------------
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(gbl);
        mainPanel.setLocation(0, 0);
//------------------mainPanel--------------------
//------------------menuPanel--------------------
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuPanel.setBackground(Color.BLUE);
        c.gridx = 1;
        c.gridy = 1;
        gbl.setConstraints(menuPanel, c);
        mainPanel.add(menuPanel);
//------------------menuPanel--------------------
//------------------leftPanel--------------------
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(gbl);
        leftPanel.setBackground(Color.RED);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridwidth  = 1;
        gbl.setConstraints(leftPanel, c);
        mainPanel.add(leftPanel);
//------------------leftPanel--------------------
//------------------rightPanel-------------------
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(gbl);
        rightPanel.setBackground(Color.RED);
        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 0;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridwidth  = 1;
        gbl.setConstraints(rightPanel, c);
        mainPanel.add(rightPanel);
//------------------rightPanel--------------------
//------------------contentPanel--------------------
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(gbl);
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.5;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.RELATIVE;
        gbl.setConstraints(scrollPane, c);
        mainPanel.add(scrollPane);
//------------------contentPanel--------------------
//------------------basePanel--------------------
        basePanel = new BaseStatPanel();
        basePanel.setVisible(false);
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth  = 1;
        gbl.setConstraints(basePanel, c);
        contentPanel.add(basePanel);
//------------------basePanel--------------------
//------------------infoPanel--------------------
        infoPanel = new InfoPanel();
        infoPanel.setVisible(false);
        c.gridx = 2;
        c.gridy = 1;
        gbl.setConstraints(infoPanel, c);
        contentPanel.add(infoPanel);
//------------------infoPanel--------------------
//------------------perksPanel--------------------
        perksPanel = new PerksPanel();
        perksPanel.setVisible(false);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        gbl.setConstraints(perksPanel, c);
        contentPanel.add(perksPanel);
//------------------perksPanel--------------------
//------------------skillsPanel--------------------
        skillsPanel = new SkillsPanel();
        skillsPanel.setVisible(false);
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        gbl.setConstraints(skillsPanel, c);
        contentPanel.add(skillsPanel);
//------------------basePanel-----------------------
//------------------menuGroup--------------------------
        JPanel menuGroup = new JPanel(new GridLayout(4, 1, 0, 20));
        menuGroup.setBackground(Color.RED);
        menuGroup.setVisible(false);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(menuGroup, c);
        rightPanel.add(menuGroup);
//------------------menuGroup--------------------------
//------------------expandButton-----------------------
        Button expandButton = new Button("<");
        expandButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                menuGroup.setVisible(!menuGroup.isVisible());
            }
        });
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 1;
        c.weighty = 1;
        gbl.setConstraints(expandButton, c);
        rightPanel.add(expandButton);
//------------------expandButton-----------------------
//------------------characterEditorButton-------------------------
        Button characterEditorButton = new Button("Character Editor");
        characterEditorButton.setFont(Resources.font31);
        menuGroup.add(characterEditorButton);
//------------------characterEditorButton-------------------------
//-------------------settingsButton-------------------------
        Button settingsButton = new Button("Settings");
        settingsButton.setFont(Resources.font31);
        menuGroup.add(settingsButton);
//------------------settingsButton-------------------------
//------------------characterListLabel-------------------------
        Label characterListLabel = new Label("Characters list");
        characterListLabel.setFont(Resources.font31);
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = 1;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(characterListLabel, c);
        leftPanel.add(characterListLabel);
//------------------characterListLabel-------------------------
//------------------characterList-------------------------
        characterList = new JList<>(DBConnect.getAllCharacter());
        characterId = allCharacterId.get(0);
        characterList.setFont(Resources.font15);
        characterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                System.out.println(e.getValueIsAdjusting());
                if (!e.getValueIsAdjusting())
                {
                    if (!basePanel.isVisible())
                    {
                        basePanel.setVisible(true);
                        infoPanel.setVisible(true);
                        perksPanel.setVisible(true);
                        skillsPanel.setVisible(true);
                    }
                    int result = -1;
                    if (isChanged)
                    {
                        result = JOptionPane.showConfirmDialog(Window.this,
                                "Есть несохранённые данные, сохранить сейчас?", "Внимание!", JOptionPane.YES_NO_CANCEL_OPTION);
                        if (result == JOptionPane.YES_OPTION)
                        {
                            isChanged = false;
                            saveAll();
                        }
                        if (result == JOptionPane.NO_OPTION)
                        {
                            isChanged = false;
                            installAll();
                        }
                        if (result == JOptionPane.CANCEL_OPTION)
                        {
                            isChanged = false;
                            characterList.setSelectedIndex(allCharacterId.indexOf(characterId));
                            isChanged = true;
                        }
                    }
                    else
                    {
                        installAll();
                    }
                }
            }
        });
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        gbl.setConstraints(characterList, c);
        leftPanel.add(characterList);
//------------------characterList------------------------
//------------------saveButton------------------------
        Button saveButton = new Button("Save");
        saveButton.setFont(Resources.font15B);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                isChanged = false;
                saveAll();
            }
        });
        menuPanel.add(saveButton);
//------------------saveButton------------------------
//------------------deleteButton------------------------
        Button deleteButton = new Button("Delete");
        deleteButton.setFont(Resources.font15B);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (characterList.getModel().getSize()-1 == 0)
                {
                    basePanel.setVisible(false);
                    infoPanel.setVisible(false);
                    perksPanel.setVisible(false);
                    skillsPanel.setVisible(false);
                }
                else
                {
                    try
                    {
                        DBConnect.deleteCharacter(allCharacterId.get(characterList.getSelectedIndex()));
                        characterList.setListData(DBConnect.getAllCharacter());
                        characterList.setSelectedIndex(0);
                        characterId = allCharacterId.get(0);
                        installAll();
                    } catch (SQLException e1)
                    {
                        e1.printStackTrace();

                    }

                }

            }
        });
        menuPanel.add(deleteButton);
//------------------deleteButton------------------------
//------------------addButton------------------------
        Button addButton = new Button("Add");
        addButton.setFont(Resources.font15B);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int i = 1;
                try
                {
                    for (int j = 0; j < allCharacterId.get(allCharacterId.size() - 1); j++)
                    {
                        System.out.println(i + " " + allCharacterId.get(j));
                        if (i != allCharacterId.get(j))
                        {
                            DBConnect.createCharacter(i);
                            break;
                        }
                        if (j+1 == allCharacterId.get(allCharacterId.size() - 1))
                        {
                            DBConnect.createCharacter(i++);
                            break;
                        }
                        i++;
                    }

                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                basePanel.clear();
                infoPanel.clear();
                perksPanel.clear();
                skillsPanel.clear();
                try
                {
                    characterList.setListData(DBConnect.getAllCharacter());
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                characterList.setSelectedIndex(i-1);
                characterId = i;
                if (!basePanel.isVisible())
                {
                    basePanel.setVisible(true);
                    infoPanel.setVisible(true);
                    perksPanel.setVisible(true);
                    skillsPanel.setVisible(true);
                }
            }
        });
        menuPanel.add(addButton);
//------------------addButton------------------------

        installAll();
        setContentPane(mainPanel);
        //setResizable(false);
        setVisible(true);
    }

    private void installStats(float[] characterStats)
    {
        (basePanel.getLabelSTCount()).setText(String.valueOf((int)characterStats[0]));
        (basePanel.getLabelDXCount()).setText(String.valueOf((int)characterStats[1]));
        (basePanel.getLabelIQCount()).setText(String.valueOf((int)characterStats[2]));
        (basePanel.getLabelHTCount()).setText(String.valueOf((int)characterStats[3]));
        (basePanel.getLabelHPCount()).setText(String.valueOf((int)characterStats[4]));
        (basePanel.getLabelWillCount()).setText(String.valueOf((int)characterStats[5]));
        (basePanel.getLabelPerCount()).setText(String.valueOf((int)characterStats[6]));
        (basePanel.getLabelFPCount()).setText(String.valueOf((int)characterStats[7]));
        (basePanel.getLabelBSCount()).setText(String.valueOf(characterStats[8]));
        (basePanel.getLabelBMCount()).setText(String.valueOf((int)characterStats[9]));
        (basePanel.getLabelBLCount()).setText(String.valueOf(((int)characterStats[0] * (int)characterStats[0])/5));
        try
        {
            (basePanel.getLabelHandDamageThrCount()).setText(DBConnect.getThrDamageOnST((int)characterStats[0]));
            (basePanel.getLabelHandDamageSwCount()).setText(DBConnect.getSwDamageOnST((int)characterStats[0]));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void installInfo (String[] characterInfo)
    {
        (infoPanel.getTextName()).setText(characterInfo[0]);
        (infoPanel.getTextAge()).setText(characterInfo[1]);
        (infoPanel.getTextInitialPoints()).setText(characterInfo[2]);
        (infoPanel.getLabelRemainingPointsCount()).setText(characterInfo[3]);
        (infoPanel.getTextDescription()).setText(characterInfo[4]);
        (infoPanel.getTextHeight()).setText(characterInfo[5]);
        (infoPanel.getTextWeight()).setText(characterInfo[6]);
        (infoPanel.getTextRace()).setText(characterInfo[7]);
    }

    private void installPerks (Object[][] characterAdvantage, Object[][] characterDisadvantage, Object[][] characterQuirk)
    {
        DefaultTableModel dtm = (DefaultTableModel) perksPanel.getTableAdvantage().getModel();
        dtm.setRowCount(0);
        for (Object[] aCharacterAdvantage : characterAdvantage)
            dtm.addRow(aCharacterAdvantage);

        dtm = (DefaultTableModel) perksPanel.getTableDisadvantage().getModel();
        dtm.setRowCount(0);
        for (Object[] aCharacterDisadvantage : characterDisadvantage)
            dtm.addRow(aCharacterDisadvantage);

        dtm = (DefaultTableModel) perksPanel.getTableQuirk().getModel();
        dtm.setRowCount(0);
        for (Object[] aCharacterQuirk : characterQuirk)
            dtm.addRow(aCharacterQuirk);

    }

    private void installSkills (Object[][] characterSkills)
    {
        DefaultTableModel dtm = (DefaultTableModel) skillsPanel.getTableSkills().getModel();
        dtm.setRowCount(0);
        for (Object[] aCharacterAdvantage : characterSkills)
            dtm.addRow(aCharacterAdvantage);
    }

    private void saveAll()
    {
        basePanel.saveStats();
        infoPanel.saveStats();
        perksPanel.saveStats();
        skillsPanel.saveStats();
        try
        {
            int id = characterId;
            characterList.setListData(DBConnect.getAllCharacter());
            characterId = id;
            characterList.setSelectedIndex(allCharacterId.indexOf(characterId));
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }

    private void installAll()
    {
        try
        {
            characterId = (characterList.isSelectionEmpty()) ? allCharacterId.get(0) : allCharacterId.get(characterList.getSelectedIndex());
            installStats(DBConnect.getCharacterStats(characterId));
            installInfo(DBConnect.getCharacterInfo(characterId));
            installPerks(DBConnect.getCharacterAdvantage(characterId), DBConnect.getCharacterDisadvantage(characterId), DBConnect.getCharacterQuirk(characterId));
            installSkills(DBConnect.getCharacterSkills(characterId));

        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
//        g.setColor(Color.BLACK);
//        for (int i = 1; i < 20; i++)
//            g.drawLine(i*200-50, 0, i*200-50, 2000);
//        for (int i = 1; i < 20; i++)
//            g.drawLine(0, i*200-50, 2000, i*200-50);
//
//        g.setColor(Color.RED);
//        for (int i = 1; i < 20; i+=2)
//            g.drawLine(i*100-50, 0, i*100-50, 2000);
//        for (int i = 1; i < 20; i+=2)
//            g.drawLine(0, i*100-50, 2000, i*100-50);


    }


}
