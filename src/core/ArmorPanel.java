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

public class ArmorPanel extends JPanel
{
    private final JTable tableArmor;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    ArmorPanel() throws SQLException {
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
        JLabel labelTitle = new JLabel("Armor");
        labelTitle.setFont(Resources.font21);
        labelTitle.setAlignmentX(LEFT_ALIGNMENT);
        labelTitle.setToolTipText("Броня");
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
                if (tableArmor.getSelectedRowCount() != 0)
                {
                    Window.isChanged = true;
                    ((DefaultTableModel) tableArmor.getModel()).removeRow(tableArmor.getSelectedRow());
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
                if (tableArmor.getSelectedRowCount() != 0)
                {
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    dialog.setTitle(String.valueOf(tableArmor.getValueAt(tableArmor.getSelectedRow(), 0)));

                    dialog.setModal(false);
                    dialog.setFont(Resources.font31);
                    dialog.setResizable(true);
                    dialog.setSize(700, 400);
                    dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                            ((screenSize.height / 2) - (dialog.getHeight() / 2)));
                    JTextArea textDescription = new JTextArea("Error");
                    try
                    {
                        textDescription.setText(DBConnect.getArmorOnName(dialog.getTitle()));
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
//------------------tableArmor-----------------------
        tableArmor = new JTable();
        tableArmor.setFont(Resources.font15);
        TableModel model = new DefaultTableModel(DBConnect.getCharacterArmor(Window.characterId),
                new Object[]{"Armor", "Zones", "DR", "Weight", "Cost"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 1) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableArmor.setModel(model);
        tableArmor.setRowHeight(25);
        tableArmor.getColumnModel().getColumn(1).setMaxWidth(250);
        tableArmor.getColumnModel().getColumn(2).setMaxWidth(50);
        tableArmor.getColumnModel().getColumn(3).setMaxWidth(50);
        tableArmor.getColumnModel().getColumn(4).setMaxWidth(50);
        tableArmor.getTableHeader().setReorderingAllowed(false);
        tableArmor.setRowSorter(new TableRowSorter<>(tableArmor.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(15, 10, 0, 10);

        gbl.setConstraints(tableArmor.getTableHeader(), c);
        add(tableArmor.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 10, 15, 10);
        gbl.setConstraints(tableArmor, c);
        add(tableArmor);

//------------------tableArmor-----------------------

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
//------------------armorList-------------------------
        String[][] armor = new String[0][];
        try {
            armor = DBConnect.getAllArmor();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String s: armor[0])
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
//------------------labelZones-------------------------
        JLabel labelZones = new JLabel();
        labelZones.setText("Zones: " + armor[1][skillsList.getSelectedIndex()]);
        labelZones.setFont(Resources.font15);
        labelZones.setToolTipText("Участки тела, которые прикрывает броня");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelZones, c);
        infoPanel.add(labelZones);
//------------------labelZones-------------------------
//------------------labelDR-------------------------
        JLabel labelDR = new JLabel();
        labelDR.setText("DR: " + armor[2][skillsList.getSelectedIndex()]);
        labelDR.setFont(Resources.font15);
        labelDR.setToolTipText("Сопротивление урону");
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelDR, c);
        infoPanel.add(labelDR);
//------------------labelDR-------------------------
//------------------labelWeight-------------------------
        JLabel labelWeight = new JLabel();
        labelWeight.setText("Weight: " + armor[3][skillsList.getSelectedIndex()]);
        labelWeight.setFont(Resources.font15);
        labelWeight.setToolTipText("Вес борни");
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        gbl.setConstraints(labelWeight, c);
        infoPanel.add(labelWeight);
//------------------labelWeight-------------------------
//------------------labelCost-------------------------
        JLabel labelCost = new JLabel();
        labelCost.setText("Cost: " + ((armor[4][skillsList.getSelectedIndex()])));
        labelCost.setFont(Resources.font15);
        labelCost.setToolTipText("Средняя стоимость брони");
        c.gridx = 2;
        c.gridy = 2;
        gbl.setConstraints(labelCost, c);
        infoPanel.add(labelCost);
//------------------labelCost-------------------------
//------------------textDescription-----------------------
        String[][] finalArmor = armor;
        JTextArea textDescription = new JTextArea();
        textDescription.setText(finalArmor[5][skillsList.getSelectedIndex()]);
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
                labelZones.setText("Zones: " + finalArmor[1][skillsList.getSelectedIndex()]);
                labelDR.setText("DR: " + finalArmor[2][skillsList.getSelectedIndex()]);
                labelWeight.setText("Weight: " + finalArmor[3][skillsList.getSelectedIndex()]);
                labelCost.setText("Cost: " + (finalArmor[4][skillsList.getSelectedIndex()]));
                textDescription.setText(finalArmor[5][skillsList.getSelectedIndex()]);
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
                dtm = (DefaultTableModel) tableArmor.getModel();
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
                        JLabel label = new JLabel("У персонажа уже имеется эта броня!");
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
                            labelZones.getText().substring(7),
                            labelDR.getText().substring(4),
                            Integer.parseInt(labelWeight.getText().substring(8)),
                            Integer.parseInt(labelCost.getText().substring(6))});
                    Window.isChanged = true;
                    dialogChoice.dispose();
                }
                Window.mathPoints();
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


    void clear()
    {
        DefaultTableModel dtm = (DefaultTableModel) tableArmor.getModel();
        dtm.setRowCount(0);
    }

    void saveStats()
    {
        String[] name = new String[tableArmor.getRowCount()];

        for (int i = 0; i < tableArmor.getRowCount(); i++)
        {
            name[i] = tableArmor.getValueAt(i, 0).toString();
        }
        try
        {
            DBConnect.saveArmor(name, Window.characterId);
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

        g.drawLine(5, tableArmor.getTableHeader().getY() - 5,
                getWidth()-5, tableArmor.getTableHeader().getY() - 5);

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


    public JTable getTableArmor() {
        return tableArmor;
    }

}