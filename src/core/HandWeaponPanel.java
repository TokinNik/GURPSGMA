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
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.AbstractDocument;

public class HandWeaponPanel extends JPanel
{
    private final JTable tableHandWeapon;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    HandWeaponPanel() throws SQLException {
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gbl);
        c.anchor = GridBagConstraints.WEST;
        c.fill   = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth  = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(15, 25, 0, 20);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        setBackground(Color.ORANGE);
//------------------labelTitle-----------------------
        JLabel labelTitle = new JLabel("HandWeapon");
        labelTitle.setFont(Resources.font21);
        labelTitle.setAlignmentX(LEFT_ALIGNMENT);
        labelTitle.setToolTipText("Оружие ближнего боя");
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
                if (tableHandWeapon.getSelectedRowCount() != 0)
                {
                    Window.isChanged = true;
                    ((DefaultTableModel) tableHandWeapon.getModel()).removeRow(tableHandWeapon.getSelectedRow());
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
                if (tableHandWeapon.getSelectedRowCount() != 0)
                {
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    dialog.setTitle(String.valueOf(tableHandWeapon.getValueAt(tableHandWeapon.getSelectedRow(), 0)));

                    dialog.setModal(false);
                    dialog.setFont(Resources.font31);
                    dialog.setResizable(true);
                    dialog.setSize(700, 400);
                    dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                            ((screenSize.height / 2) - (dialog.getHeight() / 2)));
                    JTextArea textDescription = new JTextArea("Error");
                    try
                    {
                        textDescription.setText(DBConnect.getHandWeaponOnName(dialog.getTitle()));
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
//------------------tableHandWeapon-----------------------
        tableHandWeapon = new JTable();
        tableHandWeapon.setFont(Resources.font15);
        TableModel model = new DefaultTableModel(DBConnect.getCharacterHandWeapon(Window.characterId),
                new Object[]{"Hand Weapon", "Damage", "Damage type", "Min ST", "KD", "Cost", "Weight"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 5) return Float.class;
                if (columnIndex > 2) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableHandWeapon.setModel(model);
        tableHandWeapon.setRowHeight(25);
        tableHandWeapon.getColumnModel().getColumn(1).setMaxWidth(200);
        tableHandWeapon.getColumnModel().getColumn(2).setMaxWidth(200);
        tableHandWeapon.getColumnModel().getColumn(3).setMaxWidth(200);
        tableHandWeapon.getColumnModel().getColumn(4).setMaxWidth(200);
        tableHandWeapon.getColumnModel().getColumn(5).setMaxWidth(200);
        tableHandWeapon.getColumnModel().getColumn(6).setMaxWidth(200);
        tableHandWeapon.getTableHeader().setReorderingAllowed(false);
        tableHandWeapon.setRowSorter(new TableRowSorter<>(tableHandWeapon.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(15, 20, 0, 20);

        gbl.setConstraints(tableHandWeapon.getTableHeader(), c);
        add(tableHandWeapon.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 20, 15, 20);
        gbl.setConstraints(tableHandWeapon, c);
        add(tableHandWeapon);

//------------------tableHandWeapon-----------------------

    }


    private void createDialog()
    {
        JDialog dialogChoice = new JDialog();
        dialogChoice.setTitle("Choice hand weapon");
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
        String[][] handWeapon = new String[0][];
        try {
            handWeapon = DBConnect.getAllHandWeapon();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String s: handWeapon[0])
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
//------------------labelDamage-------------------------
        JLabel labelDamage = new JLabel();
        labelDamage.setText("Damage: " + handWeapon[1][skillsList.getSelectedIndex()]);
        labelDamage.setFont(Resources.font15);
        labelDamage.setToolTipText("Урон оружия");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelDamage, c);
        infoPanel.add(labelDamage);
//------------------labelDamage-------------------------
//------------------labelType-------------------------
        JLabel labelType = new JLabel();
        labelType.setText("Damage type: " + handWeapon[2][skillsList.getSelectedIndex()]);
        labelType.setFont(Resources.font15);
        labelType.setToolTipText("Тип урона");
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelType, c);
        infoPanel.add(labelType);
//------------------labelType-------------------------
// ------------------labelMinST-------------------------
        JLabel labelMinST = new JLabel();
        labelMinST.setText("Min ST: " + handWeapon[3][skillsList.getSelectedIndex()]);
        labelMinST.setFont(Resources.font15);
        labelMinST.setToolTipText("Минимальная сила для испольнования");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelMinST, c);
        infoPanel.add(labelMinST);
//------------------labelMinST-------------------------
//------------------labelKD-------------------------
        JLabel labelKD = new JLabel();
        labelKD.setText("KD: " + handWeapon[4][skillsList.getSelectedIndex()]);
        labelKD.setFont(Resources.font15);
        labelKD.setToolTipText("Время подготовки оружия после удара");
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelKD, c);
        infoPanel.add(labelKD);
//------------------labelType-------------------------
//------------------labelWeight-------------------------
        JLabel labelWeight = new JLabel();
        labelWeight.setText("Weight: " + handWeapon[6][skillsList.getSelectedIndex()]);
        labelWeight.setFont(Resources.font15);
        labelWeight.setToolTipText("Вес борни");
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0.0;
        gbl.setConstraints(labelWeight, c);
        infoPanel.add(labelWeight);
//------------------labelWeight-------------------------
//------------------labelCost-------------------------
        JLabel labelCost = new JLabel();
        labelCost.setText("Cost: " + ((handWeapon[5][skillsList.getSelectedIndex()])));
        labelCost.setFont(Resources.font15);
        labelCost.setToolTipText("Средняя стоимость брони");
        c.gridx = 2;
        c.gridy = 3;
        gbl.setConstraints(labelCost, c);
        infoPanel.add(labelCost);
//------------------labelCost-------------------------
//------------------textDescription-----------------------
        JTextArea textDescription = new JTextArea();
        textDescription.setText(handWeapon[7][skillsList.getSelectedIndex()]);
        textDescription.setFont(Resources.font11);
        textDescription.setLineWrap(true);
        textDescription.setEditable(false);
        textDescription.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 4;
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
        String[][] finalArmor = handWeapon;
        skillsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                labelDamage.setText("Damage: " + finalArmor[1][skillsList.getSelectedIndex()]);
                labelType.setText("Damage type: " + finalArmor[2][skillsList.getSelectedIndex()]);
                labelMinST.setText("Min ST: " + finalArmor[3][skillsList.getSelectedIndex()]);
                labelKD.setText("KD: " + finalArmor[4][skillsList.getSelectedIndex()]);
                labelWeight.setText("Weight: " + finalArmor[6][skillsList.getSelectedIndex()]);
                labelCost.setText("Cost: " + (finalArmor[5][skillsList.getSelectedIndex()]));
                textDescription.setText(finalArmor[7][skillsList.getSelectedIndex()]);
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
                dtm = (DefaultTableModel) tableHandWeapon.getModel();
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
                        JLabel label = new JLabel("У персонажа уже имеется это оружие!");
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
                            labelDamage.getText().substring(8),
                            labelType.getText().substring(13),
                            Integer.parseInt(labelMinST.getText().substring(8)),
                            Integer.parseInt(labelKD.getText().substring(4)),
                            Integer.parseInt(labelCost.getText().substring(6)),
                            Float.parseFloat(labelWeight.getText().substring(8))
                    });
                            Window.isChanged = true;
                    dialogChoice.dispose();
                }
                Window.mathPoints();
            }
        });
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonAdd, c);
        infoPanel.add(buttonAdd);
//------------------buttonAdd-----------------------
//------------------buttonAddNew-----------------------
        JButton buttonAddNew = new JButton("Add new hand weapon...");
        buttonAddNew.setFont(Resources.font15);
        buttonAddNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cost = labelCost.getText();
                String dmg = labelDamage.getText();
                String wt = labelWeight.getText();
                String kd = labelKD.getText();
                String minst = labelMinST.getText();
                String type = labelType.getText();
                textDescription.setEditable(true);
                textDescription.setBackground(Color.WHITE);
                buttonAdd.setVisible(false);
                buttonAddNew.setVisible(false);
                JTextField textCost = new JTextField();
                ((AbstractDocument) textCost.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textKD = new JTextField();
                ((AbstractDocument) textKD.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textMinST = new JTextField();
                ((AbstractDocument) textMinST.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textWeight = new JTextField();
                JTextField textDamage = new JTextField();
                JMenu menuDamageType = new JMenu("Damage type");
                menuDamageType.setSelected(true);
                JRadioButtonMenuItem rb1 = new JRadioButtonMenuItem("Рубящий",false);
                menuDamageType.add(rb1);
                JRadioButtonMenuItem rb2 = new JRadioButtonMenuItem("Проникающий",false);
                menuDamageType.add(rb2);
                JRadioButtonMenuItem rb3 = new JRadioButtonMenuItem("Дробящий",false);
                menuDamageType.add(rb3);

                c.gridwidth  = 1;
                c.gridheight = 1;
                c.gridx = 1;
                c.gridy = 2;
                c.weightx = 1;
                c.weighty = 0;
                labelDamage.setText("Damage:");
                gbl.setConstraints(labelDamage, c);

                c.weightx = 1;
                c.gridx = 2;
                textDamage.setFont(Resources.font15);
                gbl.setConstraints(textDamage, c);
                infoPanel.add(textDamage);

                c.weightx = 1;
                c.gridx = 3;
                labelType.setText("Damage type:");
                gbl.setConstraints(labelType, c);

                c.weightx = 0;
                c.gridx = 4;
                menuDamageType.setFont(Resources.font15);
                gbl.setConstraints(menuDamageType, c);
                infoPanel.add(menuDamageType);

                c.gridx = 1;
                c.gridy = 3;
                labelMinST.setText("Min ST:");
                gbl.setConstraints(labelMinST, c);

                c.weightx = 0;
                c.gridx = 2;
                textMinST.setFont(Resources.font15);
                gbl.setConstraints(textMinST, c);
                infoPanel.add(textMinST);

                c.gridx = 3;
                labelKD.setText("KD:");
                gbl.setConstraints(labelKD, c);

                c.weightx = 1;
                c.gridx = 4;
                textKD.setFont(Resources.font15);
                gbl.setConstraints(textKD, c);
                infoPanel.add(textKD);

                c.gridx = 1;
                c.gridy = 4;
                labelWeight.setText("Weight:");
                gbl.setConstraints(labelWeight, c);

                c.weightx = 0;
                c.gridx = 2;
                textWeight.setFont(Resources.font15);
                gbl.setConstraints(textWeight, c);
                infoPanel.add(textWeight);

                c.gridx = 3;
                labelCost.setText("Cost:");
                gbl.setConstraints(labelCost, c);

                c.weightx = 1;
                c.gridx = 4;
                textCost.setFont(Resources.font15);
                gbl.setConstraints(textCost, c);
                infoPanel.add(textCost);

                c.gridx = 1;
                c.gridy = 5;
                c.weighty = 1;
                c.gridwidth = GridBagConstraints.REMAINDER;
                c.gridheight = GridBagConstraints.RELATIVE;
                gbl.setConstraints(scrollDescription, c);

                JLabel labelName = new JLabel("Name");
                labelName.setFont(Resources.font15);
                labelName.setToolTipText("Название брони");
                c.gridx = 1;
                c.gridy = 1;
                c.weighty = 0;
                c.gridwidth = 1;
                c.gridheight = 1;
                gbl.setConstraints(labelName, c);
                infoPanel.add(labelName);

                JTextField textName = new JTextField();
                textName.setFont(Resources.font15);
                c.gridx = 2;
                c.gridwidth = 2;
                gbl.setConstraints(textName, c);
                infoPanel.add(textName);

                JButton add = new JButton();
                add.setText("Add new hand weapon");

                JButton cancel = new JButton("Cancel");
                cancel.setFont(Resources.font15);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        listModel.addElement(textName.getText());
                        infoPanel.remove(textName);
                        infoPanel.remove(labelName);
                        infoPanel.remove(textCost);
                        infoPanel.remove(textDamage);
                        infoPanel.remove(textKD);
                        infoPanel.remove(textMinST);
                        infoPanel.remove(textWeight);
                        infoPanel.remove(menuDamageType);
                        infoPanel.remove(cancel);
                        infoPanel.remove(add);
                        textDescription.setEditable(false);
                        textDescription.setBackground(Color.LIGHT_GRAY);
                        buttonAdd.setVisible(true);
                        buttonAddNew.setVisible(true);
                        labelCost.setVisible(true);
                        c.gridwidth = 1;
                        c.gridheight = 1;
                        c.gridx = 1;
                        c.gridy = 1;
                        c.weightx = 0.0;
                        c.weighty = 0.0;
                        labelDamage.setText(dmg);
                        gbl.setConstraints(labelDamage, c);
                        c.gridx = 2;
                        labelType.setText(type);
                        gbl.setConstraints(labelType, c);
                        c.gridx = 1;
                        c.gridy = 2;
                        labelMinST.setText(minst);
                        gbl.setConstraints(labelMinST, c);
                        c.gridx = 2;
                        labelKD.setText(kd);
                        gbl.setConstraints(labelKD, c);
                        c.gridx = 1;
                        c.gridy = 3;
                        labelWeight.setText(wt);
                        gbl.setConstraints(labelWeight, c);
                        c.gridx = 2;
                        labelCost.setText(cost);
                        gbl.setConstraints(labelCost, c);
                        c.gridx = 1;
                        c.gridy = 4;
                        c.weightx = 1;
                        c.weighty = 1;
                        c.gridwidth = GridBagConstraints.REMAINDER;
                        c.gridheight = GridBagConstraints.RELATIVE;
                        try
                        {
                            textDescription.setText(DBConnect.getHandWeaponOnName(skillsList.getSelectedValue()));
                        } catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                        gbl.setConstraints(scrollDescription, c);
                    }
                });
                c.gridwidth = 1;
                c.gridx = 3;
                c.gridy = 6;
                gbl.setConstraints(cancel, c);
                infoPanel.add(cancel);

                add.setFont(Resources.font15);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        boolean can = true;
                        try
                        {
                            if (DBConnect.getHandWeaponOnName(textName.getText()).equals("null"))
                                DBConnect.addNewHandWeapon(textName.getText(),
                                        textDamage.getText(),
                                        ((menuDamageType.getItem(0).isArmed()) ? "руб," : "") +
                                                ((menuDamageType.getItem(1).isArmed()) ? "прон," : "") +
                                                ((menuDamageType.getItem(2).isArmed()) ? "дроб," : ""),
                                        textMinST.getText(),
                                        textKD.getText(),
                                        textWeight.getText(),
                                        textCost.getText(),
                                        textDescription.getText());
                            else
                            {
                                JOptionPane.showConfirmDialog(infoPanel, "Оружие с таким именем уже существует!", "Error", JOptionPane.DEFAULT_OPTION);
                                can = false;
                            }
                        } catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                        if (can)
                        {
                            dialogChoice.dispose();
                            createDialog();
                        }

                    }
                });
                c.gridwidth = 2;
                c.gridx = 1;
                c.gridy = 6;
                gbl.setConstraints(add, c);
                infoPanel.add(add);
            }
        });
        c.gridx = 2;
        c.gridy = 5;
        gbl.setConstraints(buttonAddNew, c);
        infoPanel.add(buttonAddNew);
//------------------buttonAddNew-----------------------
        dialogChoice.setVisible(true);
    }


    void clear()
    {
        DefaultTableModel dtm = (DefaultTableModel) tableHandWeapon.getModel();
        dtm.setRowCount(0);
    }

    void saveStats()
    {
        String[] name = new String[tableHandWeapon.getRowCount()];

        for (int i = 0; i < tableHandWeapon.getRowCount(); i++)
        {
            name[i] = tableHandWeapon.getValueAt(i, 0).toString();
        }
        try
        {
            DBConnect.saveHandWeapon(name, Window.characterId);
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

        g.drawLine(5, tableHandWeapon.getTableHeader().getY() - 5,
                getWidth()-5, tableHandWeapon.getTableHeader().getY() - 5);

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


    public JTable getTableHandWeapon() {
        return tableHandWeapon;
    }

}
