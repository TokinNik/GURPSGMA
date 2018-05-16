package core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class SkillsPanel extends JPanel
{
    private final JTable tableSkills;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    SkillsPanel() throws SQLException {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        c.anchor = GridBagConstraints.WEST;
        c.fill   = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(15, 15, 0, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        setBackground(Color.ORANGE);
//------------------labelTitle-----------------------
        JLabel labelTitle = new JLabel("Skills");
        labelTitle.setFont(Resources.font21);
        labelTitle.setAlignmentX(LEFT_ALIGNMENT);
        labelTitle.setToolTipText("Навыки");
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 1;
        gbl.setConstraints(labelTitle, c);
        add(labelTitle);
//------------------labelTitle-----------------------
//------------------buttonPlus-----------------------
        JButton buttonPlus = new JButton("+");
        buttonPlus.setFont(Resources.font15);
        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createDialog();
            }
        });
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.0;
        gbl.setConstraints(buttonPlus, c);
        add(buttonPlus);
//------------------buttonPlus-----------------------
//------------------buttonMius-----------------------
        JButton buttonMius = new JButton("-");
        buttonMius.setFont(Resources.font15);
        buttonMius.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Window.isChanged = true;
                ((DefaultTableModel) tableSkills.getModel()).removeRow(tableSkills.getSelectedRow());
            }
        });
        c.gridx = 3;
        c.gridy = 1;
        gbl.setConstraints(buttonMius, c);
        add(buttonMius);
//------------------buttonMius-----------------------
//------------------buttonInfo-----------------------
        JButton buttonInfo = new JButton("?");
        buttonInfo.setFont(Resources.font15);
        buttonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                JDialog dialog = new JDialog();
                dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                dialog.setTitle(String.valueOf(tableSkills.getValueAt(tableSkills.getSelectedRow(), 0)));

                dialog.setModal(false);
                dialog.setFont(Resources.font31);
                dialog.setResizable(true);
                dialog.setSize(700, 400);
                dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                        ((screenSize.height / 2) - (dialog.getHeight() / 2)));
                JTextArea textDescription = new JTextArea("Error");
                try
                {
                    textDescription.setText(DBConnect.getSkillOnName(dialog.getTitle()));
                } catch (SQLException e1)
                {
                    e1.printStackTrace();
                }
                textDescription.setFont(Resources.font15);
                textDescription.setLineWrap(true);
                textDescription.setEditable(false);
                textDescription.setBackground(Color.lightGray);
                JScrollPane scrollDescription = new JScrollPane(textDescription);
                dialog.add(scrollDescription);
                dialog.setVisible(true);

            }
        });
        c.gridx = 4;
        c.gridy = 1;
        gbl.setConstraints(buttonInfo, c);
        add(buttonInfo);
//------------------buttonInfo-----------------------
//------------------tableSkills-----------------------
        tableSkills = new JTable();
        tableSkills.setFont(Resources.font15);
        TableModel model = new DefaultTableModel(DBConnect.getCharacterSkills(Window.characterId),
                new Object[]{"Skill", "Type", "Difficulty", "Relative level", "Level", "Cost"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 3) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableSkills.setModel(model);
        tableSkills.setRowHeight(25);
        tableSkills.getColumnModel().getColumn(1).setMaxWidth(50);
        tableSkills.getColumnModel().getColumn(2).setMaxWidth(100);
        tableSkills.getColumnModel().getColumn(3).setMaxWidth(150);
        tableSkills.getColumnModel().getColumn(4).setMaxWidth(50);
        tableSkills.getColumnModel().getColumn(5).setMaxWidth(50);
        tableSkills.getTableHeader().setReorderingAllowed(false);
        tableSkills.setRowSorter(new TableRowSorter<>(tableSkills.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(15, 10, 0, 10);

        gbl.setConstraints(tableSkills.getTableHeader(), c);
        add(tableSkills.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 10, 15, 10);
        gbl.setConstraints(tableSkills, c);
        add(tableSkills);

//------------------tableSkills-----------------------

    }


    private void createDialog()
    {
        JDialog dialogChoice = new JDialog();
        dialogChoice.setTitle("Choice Skill");
        dialogChoice.setSize(900, 500);
        dialogChoice.setLocation(((screenSize.width/2) - (dialogChoice.getWidth()/2)),
                ((screenSize.height/2) - (dialogChoice.getHeight()/2)));
        dialogChoice.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialogChoice.setModal(true);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        dialogChoice.setLayout(gbl);

        c.anchor = GridBagConstraints.WEST;
        c.fill   = GridBagConstraints.BOTH;
        c.gridheight = GridBagConstraints.REMAINDER;
        c.gridwidth  = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        c.ipadx = 150;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
//------------------skillsList-------------------------
        String[][] skills = new String[0][];
        try {
            skills = DBConnect.getAllSkill();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String s: skills[0])
            listModel.addElement(s);
        JList<String> skillsList = new JList<>(listModel);
        skillsList.setFont(Resources.font15);
        skillsList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(skillsList);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(skillsList.getWidth(), dialogChoice.getHeight()));
        gbl.setConstraints(scrollPane, c);
        dialogChoice.add(scrollPane);
//------------------skillsList-------------------------
//------------------infoPanel-------------------------
        JPanel infoPanel = new JPanel(gbl);
        c.gridx = 2;
        c.gridy = 1;
        c.ipadx = 0;
        c.weightx = 1;
        c.weighty = 1;
        gbl.setConstraints(infoPanel, c);
        dialogChoice.add(infoPanel);
//------------------infoPanel-------------------------
//------------------labelDifficulty-------------------------
        JLabel labelDifficulty = new JLabel();
        labelDifficulty.setText("Difficulty: " + skills[2][skillsList.getSelectedIndex()]);
        labelDifficulty.setFont(Resources.font15);
        labelDifficulty.setToolTipText("Уровень сложности навыка");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelDifficulty, c);
        infoPanel.add(labelDifficulty);
//------------------labelDifficulty-------------------------
//------------------buttonPlus-------------------------
        JButton buttonPlus = new JButton("+");
        buttonPlus.setFont(Resources.font15);
        c.gridx = 3;
        c.gridy = 1;
        gbl.setConstraints(buttonPlus, c);
        infoPanel.add(buttonPlus);
//------------------buttonPlus-------------------------
//------------------buttonMinus-------------------------
        JButton buttonMinus = new JButton("-");
        buttonMinus.setFont(Resources.font15);
        c.gridx = 4;
        c.gridy = 1;
        gbl.setConstraints(buttonMinus, c);
        infoPanel.add(buttonMinus);
//------------------buttonMinus-------------------------
//------------------labelType-------------------------
        JLabel labelType = new JLabel();
        labelType.setText("Type: " + skills[1][skillsList.getSelectedIndex()]);
        labelType.setFont(Resources.font15);
        labelType.setToolTipText("Тип навыка");
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelType, c);
        infoPanel.add(labelType);
//------------------labelType-------------------------
//------------------labelRelativeLevel-------------------------
        JLabel labelRelativeLevel = new JLabel();
        labelRelativeLevel.setText("Relative level: " + skills[3][skillsList.getSelectedIndex()]);
        labelRelativeLevel.setFont(Resources.font15);
        labelRelativeLevel.setToolTipText("Относительный уровень навыка");
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        gbl.setConstraints(labelRelativeLevel, c);
        infoPanel.add(labelRelativeLevel);
//------------------labelRelativeLevel-------------------------
//------------------labelCurrentLevel-------------------------
        JLabel labelCurrentLevel = new JLabel();
        labelCurrentLevel.setText("Current level: " + ((skills[1][skillsList.getSelectedIndex()].equals("DX")) ?
                (Window.basePanel.getLabelDXCount().getText()) : (Window.basePanel.getLabelIQCount().getText())));
        labelCurrentLevel.setFont(Resources.font15);
        labelCurrentLevel.setToolTipText("Относительный уровень навыка");
        c.gridx = 2;
        c.gridy = 4;
        gbl.setConstraints(labelCurrentLevel, c);
        infoPanel.add(labelCurrentLevel);
//------------------labelCurrentLevel-------------------------
//------------------labelCost-------------------------
        JLabel labelCost = new JLabel();
        labelCost.setText("Cost: "+ mathSkillCost(skills[2][skillsList.getSelectedIndex()], 0, 0));
        labelCost.setFont(Resources.font15);
        labelCost.setToolTipText("Стоимость приобретения навыка");
        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 2;
        gbl.setConstraints(labelCost, c);
        infoPanel.add(labelCost);
//------------------labelCurrentLevel-------------------------
//------------------buttonPlusListener-----------------
        String[][] finalSkills = skills;
        buttonPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelCurrentLevel.setText("Current level: " + (Integer.parseInt(labelCurrentLevel.getText().substring(15)) + 1));
                labelCost.setText("Cost: "+ mathSkillCost(finalSkills[2][skillsList.getSelectedIndex()],
                        ((finalSkills[1][skillsList.getSelectedIndex()].equals("DX")) ?
                                Integer.parseInt(Window.basePanel.getLabelDXCount().getText()) :
                                Integer.parseInt(Window.basePanel.getLabelIQCount().getText())),
                        Integer.parseInt(labelCurrentLevel.getText().substring(15))));
            }
        });
//------------------buttonPlusListener-----------------
//------------------buttonMinusListener-----------------
        buttonMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelCurrentLevel.setText("Current level: " + (Integer.parseInt(labelCurrentLevel.getText().substring(15)) - 1));
                labelCost.setText("Cost: "+ mathSkillCost(finalSkills[2][skillsList.getSelectedIndex()],
                        ((finalSkills[1][skillsList.getSelectedIndex()].equals("DX")) ?
                                Integer.parseInt(Window.basePanel.getLabelDXCount().getText()) :
                                Integer.parseInt(Window.basePanel.getLabelIQCount().getText())),
                        Integer.parseInt(labelCurrentLevel.getText().substring(15))));
            }
        });
//------------------buttonMinusListener-----------------
//------------------textDescription-----------------------
        JTextArea textDescription = new JTextArea();
        textDescription.setText(finalSkills[4][skillsList.getSelectedIndex()]);
        textDescription.setFont(Resources.font11);
        textDescription.setLineWrap(true);
        textDescription.setEditable(false);
        textDescription.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = GridBagConstraints.RELATIVE;
        JScrollPane scrollDescription = new JScrollPane(textDescription);
        scrollDescription.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        gbl.setConstraints(scrollDescription, c);
        infoPanel.add(scrollDescription);
//------------------textDescription-----------------------
//------------------advListListener-----------------------
        skillsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                labelType.setText("Type: " + finalSkills[1][skillsList.getSelectedIndex()]);
                labelDifficulty.setText("Difficulty: " + finalSkills[2][skillsList.getSelectedIndex()]);
                labelRelativeLevel.setText("Relative level: " + finalSkills[3][skillsList.getSelectedIndex()]);
                textDescription.setText(finalSkills[4][skillsList.getSelectedIndex()]);
                labelCurrentLevel.setText("Current level: " + ((finalSkills[1][skillsList.getSelectedIndex()].equals("DX")) ?
                        (Window.basePanel.getLabelDXCount().getText()) : (Window.basePanel.getLabelIQCount().getText())));
                labelCost.setText("Cost: "+ mathSkillCost(finalSkills[2][skillsList.getSelectedIndex()], 0, 0));
            }
        });
//------------------advListListener-----------------------
//------------------buttonAdd-----------------------
        JButton buttonAdd = new JButton("Add");
        buttonAdd.setFont(Resources.font15);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Boolean can = true;
                DefaultTableModel dtm;
                dtm = (DefaultTableModel) tableSkills.getModel();
                for (int i = 0; i < dtm.getRowCount(); i++)
                {
                    if (dtm.getValueAt(i,0).equals(skillsList.getSelectedValue()))
                    {
                        JDialog dialog = new JDialog(dialogChoice, "Error", true);
                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        dialog.setResizable(false);
                        dialog.setLayout(null);
                        dialog.setSize(300, 150);
                        dialog.setLocation(((screenSize.width/2) - (dialog.getWidth()/2)),
                                ((screenSize.height/2) - (dialog.getHeight()/2)));
                        JLabel label = new JLabel("У персонажа уже имеется этот навык!");
                        label.setFont(Resources.font11);
                        label.setLocation(20, 20);
                        label.setSize(280, 30);
                        label.setAlignmentX(CENTER_ALIGNMENT);
                        dialog.add(label);
                        JButton button = new JButton("OK");
                        button.setFont(Resources.font15);
                        button.setLocation(100, 80);
                        button.setSize(100, 30);
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                                Window.isChanged = true;
                            }
                        });
                        dialog.add(button);
                        dialog.setVisible(true);
                        can = false;
                    }
                }
                if (can)
                {
                    dtm.addRow( new Object[]{skillsList.getSelectedValue(),
                            labelType.getText().substring(6),
                            labelDifficulty.getText().substring(12),
                            labelRelativeLevel.getText().substring(16),
                            Integer.parseInt(labelCurrentLevel.getText().substring(15)),
                            Integer.parseInt(labelCost.getText().substring(6))});
                    Window.isChanged = true;
                    dialogChoice.dispose();
                }

            }
        });
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonAdd, c);
        infoPanel.add(buttonAdd);
//------------------buttonAdd-----------------------
        dialogChoice.setVisible(true);
    }

    private String mathSkillCost(String dif, int prew, int next)
    {
        switch (dif)
        {
            case "Easy":
                switch (next - prew)
                {
                    case 0: return "1";
                    case 1: return "2";
                    case 2: return "4";
                    default:
                        if ((next - prew) > 0)
                            return String.valueOf(4*(next - prew - 1));
                        else
                            return String.valueOf("-");
                }
            case "Middle":
                switch (next - prew)
                {
                    case -1: return "1";
                    case 0: return "2";
                    case 1: return "4";
                    default:
                        if ((next - prew) > 0)
                            return String.valueOf(4*(next - prew));
                        else
                            return String.valueOf("-");
                }
            case "Hard":
                switch (next - prew)
                {
                    case -2: return "1";
                    case -1: return "2";
                    case 0: return "4";
                    default:
                        if ((next - prew) > 0)
                            return String.valueOf(4*(next - prew + 1));
                        else
                            return String.valueOf("-");
                }
            case "Very Hard":
                switch (next - prew)
                {
                    case -3: return "1";
                    case -2: return "2";
                    case -1: return "4";
                    default:
                        if ((next - prew) >= 0)
                            return String.valueOf(4*(next - prew + 2));
                        else
                            return String.valueOf("-");
                }
                default: return "-1";
        }
    }

    void clear()
    {
        DefaultTableModel dtm = (DefaultTableModel) tableSkills.getModel();
        dtm.setRowCount(0);
    }

    void saveStats()
    {
        String[] name = new String[tableSkills.getRowCount()];
        int[] level = new int[tableSkills.getRowCount()];
        int[] cost = new int[tableSkills.getRowCount()];

        for (int i = 0; i < tableSkills.getRowCount(); i++)
        {
            name[i] = tableSkills.getValueAt(i, 0).toString();
            level[i] = Integer.parseInt(tableSkills.getValueAt(i, 4).toString());
            cost[i] = Integer.parseInt(tableSkills.getValueAt(i, 5).toString());
        }
        try
        {
            DBConnect.saveSkill(name, level, cost, Window.characterId);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);

        g.drawLine(5, tableSkills.getTableHeader().getY() - 5,
                getWidth()-5, tableSkills.getTableHeader().getY() - 5);

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image im = null;
        try {
            im = ImageIO.read(new File("bg_yellow.png"));
        } catch (IOException ignored) {}
        g.drawImage(im, 0, 0, getWidth(), getHeight(), null);
    }


    public JTable getTableSkills() {
        return tableSkills;
    }

}