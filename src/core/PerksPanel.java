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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class PerksPanel extends JPanel
{
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final JTable tableAdvantage;
    private final JTable tableDisadvantage;
    private final JTable tableQuirk;

    PerksPanel() throws SQLException
    {
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
        setBackground(Color.GRAY);
        JLabel labelTitle = new JLabel("Advantages");
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                switch (tabbedPane.getSelectedIndex())
                {
                    case 0:
                        labelTitle.setText("Advantages");
                        labelTitle.setToolTipText("Преимущества");
                        break;
                    case 1:
                        labelTitle.setText("Disadvantages");
                        labelTitle.setToolTipText("Недостатки");
                        break;
                    case 2:
                        labelTitle.setText("Quirks");
                        labelTitle.setToolTipText("Причуды");
                        break;
                    default:break;
                }
            }
        });
//------------------labelTitle-----------------------
        labelTitle.setFont(Resources.font21);
        labelTitle.setAlignmentX(LEFT_ALIGNMENT);
        labelTitle.setToolTipText("Преимущества");
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
                createDialog(tabbedPane.getSelectedIndex());
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
            public void actionPerformed(ActionEvent e) {
                if (tabbedPane.getSelectedIndex() == 0 && tableAdvantage.getRowCount() > 0 && tableAdvantage.getSelectedRowCount() != 0)
                {
                    ((DefaultTableModel) tableAdvantage.getModel()).removeRow(tableAdvantage.getSelectedRow());
                    Window.isChanged = true;
                    Window.mathPoints();
                }
                if (tabbedPane.getSelectedIndex() == 1 && tableDisadvantage.getRowCount() > 0 && tableDisadvantage.getSelectedRowCount() != 0)
                {
                    ((DefaultTableModel) tableDisadvantage.getModel()).removeRow(tableDisadvantage.getSelectedRow());
                    Window.isChanged = true;
                    Window.mathPoints();
                }
                if (tabbedPane.getSelectedIndex() == 2 && tableQuirk.getRowCount() > 0 && tableQuirk.getSelectedRowCount() != 0)
                {
                    ((DefaultTableModel) tableQuirk.getModel()).removeRow(tableQuirk.getSelectedRow());
                    Window.isChanged = true;
                    Window.mathPoints();
                }
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

                if ((tabbedPane.getSelectedIndex() == 0 && tableAdvantage.getRowCount() > 0 && tableAdvantage.getSelectedRowCount() != 0) ||
                        (tabbedPane.getSelectedIndex() == 1 && tableDisadvantage.getRowCount() > 0 && tableDisadvantage.getSelectedRowCount() != 0) ||
                        (tabbedPane.getSelectedIndex() == 2 && tableQuirk.getRowCount() > 0 && tableQuirk.getSelectedRowCount() != 0))
                {
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    if (tabbedPane.getSelectedIndex() == 0)
                        dialog.setTitle(String.valueOf(tableAdvantage.getValueAt(tableAdvantage.getSelectedRow(),
                            tableAdvantage.getSelectedColumn())));
                    else if (tabbedPane.getSelectedIndex() == 1)
                        dialog.setTitle(String.valueOf(tableDisadvantage.getValueAt(tableDisadvantage.getSelectedRow(),
                            tableDisadvantage.getSelectedColumn())));
                    else
                        dialog.setTitle(String.valueOf(tableQuirk.getValueAt(tableQuirk.getSelectedRow(),
                                tableQuirk.getSelectedColumn())));
                    dialog.setModal(false);
                    dialog.setFont(Resources.font31);
                    dialog.setResizable(true);
                    dialog.setSize(700, 400);
                    dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                            ((screenSize.height / 2) - (dialog.getHeight() / 2)));
                    JTextArea textDescription = new JTextArea("Error");
                    try
                    {
                        if (tabbedPane.getSelectedIndex() == 0)
                            textDescription.setText(DBConnect.getAdvantageOnName(dialog.getTitle()));
                        else if (tabbedPane.getSelectedIndex() == 1)
                            textDescription.setText(DBConnect.getDisadvantageOnName(dialog.getTitle()));
                        else
                            textDescription.setText(DBConnect.getQuirkOnName(dialog.getTitle()));
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
            }
        });
        c.gridx = 4;
        c.gridy = 1;
        gbl.setConstraints(buttonInfo, c);
        add(buttonInfo);
//------------------buttonInfo-----------------------
//------------------tableAdvantage-----------------------
        tableAdvantage = new JTable();
        tableAdvantage.setFont(Resources.font15);
        TableModel model = new DefaultTableModel(DBConnect.getCharacterAdvantage(Window.characterId),
                new Object[]{"Advantage", "Cost", "Level"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 0) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableAdvantage.setModel(model);
        tableAdvantage.setRowHeight(25);
        tableAdvantage.getColumnModel().getColumn(1).setMaxWidth(50);
        tableAdvantage.getColumnModel().getColumn(2).setMaxWidth(50);
        tableAdvantage.getTableHeader().setReorderingAllowed(false);
        tableAdvantage.setRowSorter(new TableRowSorter<>(tableAdvantage.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(15, 10, 0, 10);
        JPanel containerPanel1 = new JPanel(gbl);
        gbl.setConstraints(tableAdvantage.getTableHeader(), c);
        containerPanel1.add(tableAdvantage.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 10, 15, 10);
        gbl.setConstraints(tableAdvantage, c);
        containerPanel1.add(tableAdvantage);

        tabbedPane.addTab("Advantages", containerPanel1);
        gbl.setConstraints(tabbedPane, c);
        add(tabbedPane);
//------------------tableAdvantage-----------------------
//------------------tableDisadvantage-----------------------
        tableDisadvantage = new JTable();
        tableDisadvantage.setFont(Resources.font15);
        model = new DefaultTableModel(DBConnect.getCharacterDisadvantage(Window.characterId),
                new Object[]{"Disadvantage", "Cost", "Level"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 0) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableDisadvantage.setModel(model);
        tableDisadvantage.setRowHeight(25);
        tableDisadvantage.getColumnModel().getColumn(1).setMaxWidth(50);
        tableDisadvantage.getColumnModel().getColumn(2).setMaxWidth(50);
        tableDisadvantage.getTableHeader().setReorderingAllowed(false);
        tableDisadvantage.setRowSorter(new TableRowSorter<>(tableDisadvantage.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(15, 10, 0, 10);
        JPanel containerPanel2 = new JPanel(gbl);
        gbl.setConstraints(tableDisadvantage.getTableHeader(), c);
        containerPanel2.add(tableDisadvantage.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 10, 15, 10);
        gbl.setConstraints(tableDisadvantage, c);
        containerPanel2.add(tableDisadvantage);
        tabbedPane.addTab("Disadvantages", containerPanel2);
//------------------tableDisadvantage-----------------------
//------------------tableQuirk-----------------------
        tableQuirk = new JTable();
        tableQuirk.setFont(Resources.font15);
        model = new DefaultTableModel(DBConnect.getCharacterQuirk(Window.characterId),
                new Object[]{"Quirk", "Cost", "Description"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 0) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableQuirk.setModel(model);
        tableQuirk.setRowHeight(25);
        tableQuirk.getColumnModel().getColumn(1).setMaxWidth(50);
        tableQuirk.removeColumn(tableQuirk.getColumnModel().getColumn(2));
        tableQuirk.getTableHeader().setReorderingAllowed(false);
        tableQuirk.setRowSorter(new TableRowSorter<>(tableQuirk.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(15, 10, 0, 10);
        JPanel containerPanel3 = new JPanel(gbl);
        gbl.setConstraints(tableQuirk.getTableHeader(), c);
        containerPanel3.add(tableQuirk.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 10, 15, 10);
        gbl.setConstraints(tableQuirk, c);
        containerPanel3.add(tableQuirk);
        tabbedPane.addTab("Quirks", containerPanel3);
//------------------tableQuirk-----------------------

    }

    String mathPoints()
    {
        StringBuilder total = new StringBuilder();
        int buf = 0;
        int points = 0;
        total.append("|| Perks ||\n");
        total.append("< Advantage >\n");
        for (int i = 0; i < tableAdvantage.getRowCount(); i++)
        {
            total.append("|(").append(tableAdvantage.getValueAt(i, 0).toString()).append(") :").append(tableAdvantage.getValueAt(i, 1).toString()).append("|\n");
            buf += Integer.parseInt(tableAdvantage.getValueAt(i,1).toString());
        }
        total.append("< Total advantage: ").append(buf).append(" >\n\n");
        points += buf;
        buf = 0;
        total.append("< Disadvantage >\n");
        for (int i = 0; i < tableDisadvantage.getRowCount(); i++)
        {
            total.append("|(").append(tableDisadvantage.getValueAt(i, 0).toString()).append(") :").append(tableDisadvantage.getValueAt(i, 1).toString()).append("|\n");
            buf += Integer.parseInt(tableDisadvantage.getValueAt(i,1).toString());
        }
        total.append("< Total disadvantage: ").append(buf).append(" >\n\n");
        points += buf;
        buf = 0;
        total.append("< Quirk >\n");
        for (int i = 0; i < tableQuirk.getRowCount(); i++)
        {
            total.append("|(").append(tableQuirk.getValueAt(i, 0).toString()).append(") :").append(tableQuirk.getValueAt(i, 1).toString()).append("|\n");
            buf += Integer.parseInt(tableQuirk.getValueAt(i,1).toString());
        }
        total.append("< Total quirk: ").append(buf).append(" >\n");
        points += buf;
        total.append("|| Total Perks:").append(points).append(" ||");
        Window.totalPoints[0] -= Window.totalPoints[2];
        Window.totalPoints[2] = points;
        Window.totalPoints[0] += points;
        if (!Window.infoPanel.getTextInitialPoints().getText().isEmpty())
            Window.infoPanel.getLabelRemainingPointsCount().setText(String.valueOf(Integer.parseInt(Window.infoPanel.getTextInitialPoints().getText()) - Window.totalPoints[0]));
        return total.toString();
    }

    void clear()
    {
        DefaultTableModel dtm = (DefaultTableModel) tableAdvantage.getModel();
        dtm.setRowCount(0);
        dtm = (DefaultTableModel) tableDisadvantage.getModel();
        dtm.setRowCount(0);
        dtm = (DefaultTableModel) tableQuirk.getModel();
        dtm.setRowCount(0);
    }

    void saveStats()
    {
        String[] nameAdv = new String[tableAdvantage.getRowCount()];
        int[] levelAdv = new int[tableAdvantage.getRowCount()];
        String[] nameDisAdv = new String[tableDisadvantage.getRowCount()];
        int[] levelDisAdv = new int[tableDisadvantage.getRowCount()];
        String[] nameQuirk = new String[tableQuirk.getRowCount()];
        String [] descriptionQuirk = new String[tableQuirk.getRowCount()];

        for (int i = 0; i < tableAdvantage.getRowCount(); i++)
        {
            nameAdv[i] = tableAdvantage.getValueAt(i, 0).toString();
            levelAdv[i] = Integer.parseInt(tableAdvantage.getValueAt(i, 2).toString());
        }
        for (int i = 0; i < tableDisadvantage.getRowCount(); i++)
        {
            nameDisAdv[i] = tableDisadvantage.getValueAt(i, 0).toString();
            levelDisAdv[i] = Integer.parseInt(tableDisadvantage.getValueAt(i, 2).toString());
        }
        for (int i = 0; i < tableQuirk.getRowCount(); i++)
        {
            nameQuirk[i] = tableQuirk.getValueAt(i, 0).toString();
            descriptionQuirk[i] = tableQuirk.getModel().getValueAt(i, 2).toString();
        }
        try
        {
            DBConnect.saveAdvantage(nameAdv, levelAdv, Window.characterId);
            DBConnect.saveDisadvantage(nameDisAdv, levelDisAdv, Window.characterId);
            DBConnect.saveQuirk(nameQuirk, descriptionQuirk, Window.characterId);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private void createDialog(int mode)
    {
        JDialog dialogChoice = new JDialog();
        if (mode == 0)
            dialogChoice.setTitle("Choice advantage");
        else if (mode == 1)
            dialogChoice.setTitle("Choice disadvantage");
        else
            dialogChoice.setTitle("Choice quirk");

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
//------------------advList-------------------------
        String[][] adv = new String[0][];
        try {
            if (mode == 0)
                adv = DBConnect.getAllAdvantage();
            else if (mode == 1)
                adv = DBConnect.getAllDisadvantage();
            else
                adv = DBConnect.getAllQuirk();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String s: adv[0])
            listModel.addElement(s);
        JList<String> advList = new JList<>(listModel);
        advList.setFont(Resources.font15);
        advList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(advList);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(advList.getWidth(), dialogChoice.getHeight()));
        gbl.setConstraints(scrollPane, c);
        dialogChoice.add(scrollPane);
//------------------advList-------------------------
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
//------------------labelCost-------------------------
        JLabel labelCost = new JLabel("Cost: " + ((adv.length == 2) ? "1" : adv[1][0]));
        labelCost.setFont(Resources.font15);
        labelCost.setToolTipText("Стоимость преимущества");
        c.gridwidth  = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelCost, c);
        infoPanel.add(labelCost);
//------------------labelCost-------------------------
        JLabel labelMaxLevel = new JLabel();
        JLabel labelCurrentLevel = new JLabel();
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JTextArea textDescription = new JTextArea();
        JButton buttonAdd = new JButton("Add");
//------------------labelMaxLevel-------------------------
        if (mode != 2)
        {
            if (!adv[2][0].equals("100"))
                labelMaxLevel.setText("Max level: " + adv[2][0]);
            else
                labelMaxLevel.setText("");
            labelMaxLevel.setFont(Resources.font15);
            labelMaxLevel.setToolTipText("Максимальный уровень преимущества");
            c.gridwidth = 1;
            c.gridx = 2;
            c.gridy = 1;
            c.weightx = 0.0;
            c.weighty = 0.0;
            gbl.setConstraints(labelMaxLevel, c);
            infoPanel.add(labelMaxLevel);

//------------------labelMaxLevel-------------------------
//------------------buttonPlus-------------------------
            buttonPlus.setFont(Resources.font15);
            c.gridx = 4;
            c.gridy = 1;
            gbl.setConstraints(buttonPlus, c);
            infoPanel.add(buttonPlus);
//------------------buttonPlus-------------------------
//------------------buttonMinus-------------------------
            buttonMinus.setFont(Resources.font15);
            c.gridx = 5;
            c.gridy = 1;
            gbl.setConstraints(buttonMinus, c);
            infoPanel.add(buttonMinus);
//------------------buttonMinus-------------------------
//------------------labelCurrentLevel-------------------------
            if (!adv[2][0].equals("100"))
            {
                buttonPlus.setVisible(false);
                buttonMinus.setVisible(false);
            } else
            {
                buttonPlus.setVisible(true);
                buttonMinus.setVisible(true);
            }


            labelCurrentLevel.setText("Current level: 1");
            labelCurrentLevel.setFont(Resources.font15);
            labelCurrentLevel.setToolTipText("Выбраный уровень преимущества");
            c.gridwidth = 1;
            c.gridx = 3;
            c.gridy = 1;
            c.weightx = 1;
            c.weighty = 0.0;
            gbl.setConstraints(labelCurrentLevel, c);
            infoPanel.add(labelCurrentLevel);
//------------------labelCurrentLevel-------------------------
//------------------buttonPlusListener-----------------
            String[][] finalAdv1 = adv;
            buttonPlus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = Integer.parseInt(labelCurrentLevel.getText().substring(15));
                    if (labelMaxLevel.getText().equals("") && num < 100)
                    {
                        labelCurrentLevel.setText("Current level: " + String.valueOf(num + 1));
                        labelCost.setText("Cost: " + String.valueOf((num + 1) * Integer.parseInt(finalAdv1[1][advList.getSelectedIndex()])));
                    } else if (num <= Integer.parseInt(labelMaxLevel.getText().substring(11)))
                    {
                        labelCurrentLevel.setText("Current level: " + String.valueOf(num + 1));
                        labelCost.setText("Cost: " + String.valueOf((num + 1) * Integer.parseInt(finalAdv1[1][advList.getSelectedIndex()])));
                    }
                }
            });
//------------------buttonPlusListener-----------------
//------------------buttonMinusListener-----------------
            buttonMinus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int num = Integer.parseInt(labelCurrentLevel.getText().substring(15));
                    if (num != 1)
                    {
                        labelCurrentLevel.setText("Current level: " + String.valueOf(num - 1));
                        labelCost.setText("Cost: " + String.valueOf((num - 1) * Integer.parseInt(finalAdv1[1][advList.getSelectedIndex()])));
                    }
                }
            });
        }
//------------------buttonMinusListener-----------------
        else
        {
            buttonPlus.setFont(Resources.font15);
            buttonPlus.setText("Add New Quirk...");
            buttonPlus.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    buttonPlus.setVisible(false);
                    textDescription.setEditable(true);
                    textDescription.setBackground(Color.WHITE);
                    buttonAdd.setVisible(false);
                    JLabel label = new JLabel("Name");
                    label.setFont(Resources.font15);
                    label.setToolTipText("Название причуды");
                    c.gridx = 2;
                    c.gridy = 1;
                    gbl.setConstraints(label, c);
                    infoPanel.add(label);
                    JTextField name = new JTextField();
                    name.setFont(Resources.font15);
                    c.gridx = 3;
                    c.gridy = 1;
                    gbl.setConstraints(name, c);
                    infoPanel.add(name);
                    JButton add = new JButton("Add new quirk");
                    JButton cancel = new JButton("Cancel");
                    cancel.setFont(Resources.font15);
                    cancel.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            infoPanel.remove(name);
                            infoPanel.remove(label);
                            infoPanel.remove(cancel);
                            infoPanel.remove(add);
                            textDescription.setEditable(false);
                            textDescription.setBackground(Color.LIGHT_GRAY);
                            buttonPlus.setVisible(true);
                        }
                    });
                    c.gridx = 2;
                    c.gridy = 3;
                    gbl.setConstraints(cancel, c);
                    infoPanel.add(cancel);
                    add.setFont(Resources.font15);
                    add.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try
                            {
                                DBConnect.saveQuirk(new String[]{name.getText()}, new String[]{textDescription.getText()}, 1);
                            } catch (SQLException e1)
                            {
                                e1.printStackTrace();
                            }
                            listModel.addElement(name.getText());
                            int index = listModel.size() - 1;
                            advList.setSelectedIndex(index);
                            advList.ensureIndexIsVisible(index);
                            infoPanel.remove(name);
                            infoPanel.remove(label);
                            infoPanel.remove(add);
                            infoPanel.remove(cancel);
                            textDescription.setEditable(false);
                            textDescription.setBackground(Color.LIGHT_GRAY);
                            buttonPlus.setVisible(true);
                        }
                    });
                    c.gridx = 1;
                    c.gridy = 3;
                    gbl.setConstraints(add, c);
                    infoPanel.add(add);
                }
            });
            c.gridx = 2;
            c.gridy = 1;
            gbl.setConstraints(buttonPlus, c);
            infoPanel.add(buttonPlus);
        }
//------------------textDescription-----------------------
        if (mode < 2)
            textDescription.setText(adv[3][0]);
        else
            textDescription.setText(adv[1][0]);
        textDescription.setFont(Resources.font11);
        textDescription.setLineWrap(true);
        textDescription.setEditable(false);
        textDescription.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 2;
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
        String[][] finalAdv = adv;
        advList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                if (!e.getValueIsAdjusting())
                {
                    if (mode != 2)
                    {
                        labelCost.setText("Cost: " + finalAdv[1][advList.getSelectedIndex()]);
                        if (!finalAdv[2][advList.getSelectedIndex()].equals("100"))
                        {
                            labelMaxLevel.setText("Max level: " + finalAdv[2][advList.getSelectedIndex()]);
                            buttonPlus.setVisible(false);
                            buttonMinus.setVisible(false);
                        } else
                        {
                            labelMaxLevel.setText("");
                            buttonPlus.setVisible(true);
                            buttonMinus.setVisible(true);
                        }
                        labelCurrentLevel.setText("Current level: 1");
                        textDescription.setText(finalAdv[3][advList.getSelectedIndex()]);
                    }
                    else
                        try
                        {
                            textDescription.setText(finalAdv[1][advList.getSelectedIndex()]);
                        }catch (ArrayIndexOutOfBoundsException e2)
                        {
                            try
                            {
                                textDescription.setText(DBConnect.getQuirkOnName(advList.getSelectedValue()));
                            } catch (SQLException e1)
                            {
                                e1.printStackTrace();
                            }
                        }
                    if (!buttonAdd.isVisible())
                        buttonAdd.setVisible(true);
                }

            }
        });
//------------------advListListener-----------------------
//------------------buttonAdd-----------------------
        buttonAdd.setFont(Resources.font15);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Boolean can = true;
                DefaultTableModel dtm;
                if (mode == 0)
                    dtm = (DefaultTableModel) tableAdvantage.getModel();
                else if (mode == 1)
                    dtm = (DefaultTableModel) tableDisadvantage.getModel();
                else
                    dtm = (DefaultTableModel) tableQuirk.getModel();
                for (int i = 0; i < dtm.getRowCount(); i++)
                {
                    if (dtm.getValueAt(i,0).equals(advList.getSelectedValue()))
                    {
                        JDialog dialog = new JDialog(dialogChoice, "Error", true);
                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        dialog.setResizable(false);
                        dialog.setLayout(null);
                        dialog.setSize(300, 150);
                        dialog.setLocation(((screenSize.width/2) - (dialog.getWidth()/2)),
                                ((screenSize.height/2) - (dialog.getHeight()/2)));
                        JLabel label = new JLabel("У персонажа уже имеется это преимущество!");
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
                            public void actionPerformed(ActionEvent e)
                            {
                                Window.isChanged = true;
                                dialog.dispose();
                            }
                        });
                        dialog.add(button);
                        dialog.setVisible(true);
                        can = false;
                    }
                }
                if (can)
                {
                    if (mode < 2)
                    {
                        dtm.addRow( new Object[]{advList.getSelectedValue(), Integer.parseInt(labelCost.getText().substring(6)),
                                Integer.parseInt(labelCurrentLevel.getText().substring(15))});
                    }
                    else
                        dtm.addRow( new Object[]{advList.getSelectedValue(), 1, textDescription.getText()});
                    Window.isChanged = true;
                    dialogChoice.dispose();
                }
                Window.mathPoints();
            }
        });
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonAdd, c);
        infoPanel.add(buttonAdd);
//------------------buttonAdd-----------------------
        dialogChoice.setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image im = null;
        try {
            im = ImageIO.read(Resources.BACKGROUND);
        } catch (IOException ignored) {}
        g.drawImage(im, 0, 0, getWidth(), getHeight(), null);
    }

    JTable getTableAdvantage() {
        return tableAdvantage;
    }

    JTable getTableDisadvantage() {
        return tableDisadvantage;
    }

    public JTable getTableQuirk() {
        return tableQuirk;
    }

}

