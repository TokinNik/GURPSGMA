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
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                    dialog.setModal(false);
                    dialog.setFont(Resources.font31);
                    dialog.setResizable(true);
                    dialog.setSize(700, 400);
                    dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                            ((screenSize.height / 2) - (dialog.getHeight() / 2)));
                    JTextArea textDescription = new JTextArea("Error");
                    if (tableArmor.getSelectedRowCount() != 0)
                    {
                        dialog.setTitle(String.valueOf(tableArmor.getValueAt(tableArmor.getSelectedRow(), 0)));
                        try
                        {
                            textDescription.setText(DBConnect.getArmorOnName(dialog.getTitle()));
                        } catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                    }
                    else
                    {
                        dialog.setTitle(String.valueOf("Armor"));
                        textDescription.setText("Это доспех, его элемент или одежда, защищающая некоторые участки тела\nНапример: кольчуга, меховой жилет, стальные наручи, бронежилет...");
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
//------------------tableArmor-----------------------
        tableArmor = new JTable();
        tableArmor.setFont(Resources.font15);
        tableArmor.setSelectionBackground(Resources.GLASS_GREEN);
        tableArmor.setSelectionForeground(Color.BLACK);
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


    private void createDialog() {
        JDialog dialogChoice = new JDialog();
        dialogChoice.setTitle("Choice armor");
        dialogChoice.setSize(900, 500);
        dialogChoice.setLocation(((screenSize.width / 2) - (dialogChoice.getWidth() / 2)),
                ((screenSize.height / 2) - (dialogChoice.getHeight() / 2)));
        dialogChoice.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialogChoice.setModal(true);
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        dialogChoice.setLayout(gbl);

        c.anchor = GridBagConstraints.WEST;
        c.fill = GridBagConstraints.BOTH;
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridwidth = 3;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10, 10, 10, 10);
        c.ipadx = 200;
        c.ipady = 0;
        c.weightx = 0;
        c.weighty = 1;
//------------------armorList-------------------------
        String[][] armor = new String[0][];
        try
        {
            armor = DBConnect.getAllArmor();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String s : armor[0])
            listModel.addElement(s);
        JList<String> armorList = new JList<>(listModel);
        armorList.setFont(Resources.font15);
        armorList.setSelectedIndex(0);
        JScrollPane scrollPane = new JScrollPane(armorList);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setMinimumSize(new Dimension(armorList.getWidth(), dialogChoice.getHeight()));
        gbl.setConstraints(scrollPane, c);
        dialogChoice.add(scrollPane);
//------------------armorList-------------------------
//------------------infoPanel-------------------------
        JPanel infoPanel = new JPanel(gbl);
        c.gridx = 4;
        c.gridy = 1;
        c.ipadx = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridheight = 2;
        c.gridwidth = GridBagConstraints.RELATIVE;
        gbl.setConstraints(infoPanel, c);
        dialogChoice.add(infoPanel);
//------------------infoPanel-------------------------
//------------------labelZones-------------------------
        JLabel labelZones = new JLabel();
        labelZones.setText("Zones: " + armor[1][armorList.getSelectedIndex()]);
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
        labelDR.setText("DR: " + armor[2][armorList.getSelectedIndex()]);
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
        labelWeight.setText("Weight: " + armor[3][armorList.getSelectedIndex()]);
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
        labelCost.setText("Cost: " + ((armor[4][armorList.getSelectedIndex()])));
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
        textDescription.setText(finalArmor[5][armorList.getSelectedIndex()]);
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
        armorList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                labelZones.setText("Zones: " + finalArmor[1][armorList.getSelectedIndex()]);
                labelDR.setText("DR: " + finalArmor[2][armorList.getSelectedIndex()]);
                labelWeight.setText("Weight: " + finalArmor[3][armorList.getSelectedIndex()]);
                labelCost.setText("Cost: " + (finalArmor[4][armorList.getSelectedIndex()]));
                textDescription.setText(finalArmor[5][armorList.getSelectedIndex()]);
            }
        });
//------------------advListListener-----------------------
//------------------buttonAdd-----------------------
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.setFont(Resources.font15);
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean can = true;
                DefaultTableModel dtm;
                dtm = (DefaultTableModel) tableArmor.getModel();
                for (int i = 0; i < dtm.getRowCount(); i++)
                {
                    if (dtm.getValueAt(i, 0).equals(armorList.getSelectedValue()))
                    {
                        JDialog dialog = new JDialog(dialogChoice, "Error", true);
                        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                        dialog.setResizable(false);
                        dialog.setLayout(null);
                        dialog.setSize(300, 150);
                        dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                                ((screenSize.height / 2) - (dialog.getHeight() / 2)));
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
                    dtm.addRow(new Object[]{armorList.getSelectedValue(),
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
//------------------buttonAddNew-----------------------
        JButton buttonAddNew = new JButton("+");
        JButton buttonDelete = new JButton("-");
        JButton buttonEdit = new JButton("☐");
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                boolean edit = e.getSource().equals(buttonEdit);
                String zone = labelZones.getText();
                String dr = labelDR.getText();
                String cost = labelCost.getText();
                String wt = labelWeight.getText();
                String oldName = armorList.getSelectedValue();
                textDescription.setEditable(true);
                textDescription.setBackground(Color.WHITE);
                if (!edit) textDescription.setText("");
                buttonAdd.setVisible(false);
                buttonAddNew.setVisible(false);
                buttonDelete.setVisible(false);
                buttonEdit.setVisible(false);
                scrollPane.setVisible(false);
                JTextField textCost = new JTextField(edit ? labelCost.getText().substring(6) : "");
                ((AbstractDocument) textCost.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textDR = new JTextField(edit ? labelDR.getText().substring(4) : "");
                ((AbstractDocument) textDR.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textWeight = new JTextField(edit ? labelWeight.getText().substring(8) : "");
                ((AbstractDocument) textWeight.getDocument()).setDocumentFilter(new FloatDocumentFilter(textWeight, false));
                JMenuBar menuBarZones = new JMenuBar();
                menuBarZones.setFont(Resources.font15);
                JMenu menuZones = new JMenu("Zones");
                menuZones.setSelected(true);
                Boolean[] zones = new Boolean[]{false, false, false, false, false, false, false, false, false, false, false, false};
                JRadioButtonMenuItem rb3 = new JRadioButtonMenuItem("3 (Глаза)", false);
                rb3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[0] = rb3.isArmed();
                    }
                });
                menuZones.add(rb3);
                JRadioButtonMenuItem rb4 = new JRadioButtonMenuItem("4 (Лицо)", false);
                rb4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[1] = rb4.isArmed();
                    }
                });
                menuZones.add(rb4);
                JRadioButtonMenuItem rb5 = new JRadioButtonMenuItem("5 (Череп)", false);
                rb5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[2] = rb5.isArmed();
                    }
                });
                menuZones.add(rb5);
                JRadioButtonMenuItem rb6 = new JRadioButtonMenuItem("6 (Левая (или ближняя) рука)", false);
                rb6.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[3] = rb6.isArmed();
                    }
                });
                menuZones.add(rb6);
                JRadioButtonMenuItem rb7 = new JRadioButtonMenuItem("7 (Кисти рук)", false);
                rb7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[4] = rb7.isArmed();
                    }
                });
                menuZones.add(rb7);
                JRadioButtonMenuItem rb8 = new JRadioButtonMenuItem("8 (Правая (или дальняя) рука)", false);
                rb8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[5] = rb8.isArmed();
                    }
                });
                menuZones.add(rb8);
                JRadioButtonMenuItem rb9 = new JRadioButtonMenuItem("9 (Грудь)", false);
                rb9.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[6] = rb9.isArmed();
                    }
                });
                menuZones.add(rb9);
                JRadioButtonMenuItem rb10 = new JRadioButtonMenuItem("10 (Торс)", false);
                rb10.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[7] = rb10.isArmed();
                    }
                });
                menuZones.add(rb10);
                JRadioButtonMenuItem rb11 = new JRadioButtonMenuItem("11 (Пах)", false);
                rb11.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[8] = rb11.isArmed();
                    }
                });
                menuZones.add(rb11);
                JRadioButtonMenuItem rb12 = new JRadioButtonMenuItem("12 (Правая (или дальняя) нога)", false);
                rb12.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[9] = rb12.isArmed();
                    }
                });
                menuZones.add(rb12);
                JRadioButtonMenuItem rb1314 = new JRadioButtonMenuItem("13,14 (Левая (или ближняя) нога)", false);
                rb1314.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[10] = rb1314.isArmed();
                    }
                });
                menuZones.add(rb1314);
                JRadioButtonMenuItem rb1718 = new JRadioButtonMenuItem("17,18 (Сердце или жизненно важные органы)", false);
                rb1718.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        zones[11] = rb1718.isArmed();
                    }
                });
                menuZones.add(rb1718);
                menuBarZones.add(menuZones);

                if (edit)
                {
                    String oldZones = labelZones.getText().substring(7);
                    while (!oldZones.isEmpty())
                    {
                        if (oldZones.length() > 1 && oldZones.substring(0, 2).contains(","))
                        {
                            switch (oldZones.substring(0, 1))
                            {
                                case "3":
                                    rb3.setSelected(true);
                                    zones[0] = true;
                                    break;
                                case "4":
                                    rb4.setSelected(true);
                                    zones[1] = true;
                                    break;
                                case "5":
                                    rb5.setSelected(true);
                                    zones[2] = true;
                                    break;
                                case "6":
                                    rb6.setSelected(true);
                                    zones[3] = true;
                                    break;
                                case "7":
                                    rb7.setSelected(true);
                                    zones[4] = true;
                                    break;
                                case "8":
                                    rb8.setSelected(true);
                                    zones[5] = true;
                                    break;
                                case "9":
                                    rb9.setSelected(true);
                                    zones[6] = true;
                                    break;
                                default:
                                    break;
                            }
                            oldZones = oldZones.substring(2);
                        } else if (oldZones.length() > 2 && oldZones.substring(0, 3).contains(","))
                        {
                            switch (oldZones.substring(0, 2))
                            {
                                case "10":
                                    rb10.setSelected(true);
                                    zones[7] = true;
                                    break;
                                case "11":
                                    rb11.setSelected(true);
                                    zones[8] = true;
                                    break;
                                case "12":
                                    rb12.setSelected(true);
                                    zones[9] = true;
                                    break;
                                case "13":
                                    rb1314.setSelected(true);
                                    zones[10] = true;
                                    break;
                                case "17":
                                    rb1718.setSelected(true);
                                    zones[11] = true;
                                    break;
                                default:
                                    break;
                            }
                            oldZones = oldZones.substring(3);
                        } else
                            oldZones = oldZones.substring(1);
                    }
                }

                c.gridwidth = 1;
                c.gridheight = 1;
                c.gridx = 1;
                c.gridy = 2;
                c.weightx = 1;
                c.weighty = 0;
                labelZones.setText("Zones:");
                gbl.setConstraints(labelZones, c);

                c.weightx = 0;
                c.gridx = 2;
                menuBarZones.setFont(Resources.font15);
                gbl.setConstraints(menuBarZones, c);
                infoPanel.add(menuBarZones);

                c.weightx = 1;
                c.gridx = 3;
                labelDR.setText("DR:");
                gbl.setConstraints(labelDR, c);

                c.weightx = 0;
                c.gridx = 4;
                textDR.setFont(Resources.font15);
                gbl.setConstraints(textDR, c);
                infoPanel.add(textDR);

                c.gridx = 1;
                c.gridy = 3;
                labelCost.setText("Cost:");
                gbl.setConstraints(labelCost, c);

                c.weightx = 0;
                c.gridx = 2;
                textCost.setFont(Resources.font15);
                gbl.setConstraints(textCost, c);
                infoPanel.add(textCost);

                c.gridx = 3;
                labelWeight.setText("Weight:");
                gbl.setConstraints(labelWeight, c);

                c.weightx = 1;
                c.gridx = 4;
                textWeight.setFont(Resources.font15);
                gbl.setConstraints(textWeight, c);
                infoPanel.add(textWeight);

                c.gridx = 1;
                c.gridy = 4;
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

                JTextField textName = new JTextField(edit ? oldName : "");
                textName.setFont(Resources.font15);
                c.gridx = 2;
                c.gridwidth = 2;
                gbl.setConstraints(textName, c);
                infoPanel.add(textName);

                JButton add = new JButton();
                if (edit)
                    add.setText("Update");
                else
                    add.setText("Add new armor");

                JButton cancel = new JButton("Cancel");
                cancel.setFont(Resources.font15);
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        infoPanel.remove(textName);
                        infoPanel.remove(labelName);
                        infoPanel.remove(cancel);
                        infoPanel.remove(add);
                        textDescription.setEditable(false);
                        textDescription.setBackground(Color.LIGHT_GRAY);
                        buttonAdd.setVisible(true);
                        buttonAddNew.setVisible(true);
                        buttonDelete.setVisible(true);
                        buttonEdit.setVisible(true);
                        scrollPane.setVisible(true);
                        infoPanel.remove(textCost);
                        infoPanel.remove(menuBarZones);
                        infoPanel.remove(textDR);
                        infoPanel.remove(textWeight);
                        labelCost.setVisible(true);
                        c.gridwidth = 1;
                        c.gridheight = 1;
                        c.gridx = 1;
                        c.gridy = 1;
                        c.weightx = 0.0;
                        c.weighty = 0.0;
                        labelZones.setText(zone);
                        gbl.setConstraints(labelZones, c);
                        c.gridx = 2;
                        labelDR.setText(dr);
                        gbl.setConstraints(labelDR, c);
                        c.gridx = 1;
                        c.gridy = 2;
                        labelCost.setText(cost);
                        gbl.setConstraints(labelCost, c);
                        c.gridx = 2;
                        labelWeight.setText(wt);
                        gbl.setConstraints(labelWeight, c);
                        c.gridx = 1;
                        c.gridy = 3;
                        c.weightx = 1;
                        c.weighty = 1;
                        c.gridwidth = GridBagConstraints.REMAINDER;
                        c.gridheight = GridBagConstraints.RELATIVE;
                        try
                        {
                            textDescription.setText(DBConnect.getArmorOnName(armorList.getSelectedValue()));
                        } catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                        gbl.setConstraints(scrollDescription, c);
                    }
                });
                c.gridwidth = 1;
                c.gridx = 3;
                c.gridy = 5;
                gbl.setConstraints(cancel, c);
                infoPanel.add(cancel);

                add.setFont(Resources.font15);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean can = false;
                        try
                        {
                            String zonesOut = ((((zones[0]) ? "3," : "") +
                                    ((zones[1]) ? "4," : "") +
                                    ((zones[2]) ? "5," : "") +
                                    ((zones[3]) ? "6," : "") +
                                    ((zones[4]) ? "7," : "") +
                                    ((zones[5]) ? "8," : "") +
                                    ((zones[6]) ? "9," : "") +
                                    ((zones[7]) ? "10," : "") +
                                    ((zones[8]) ? "11," : "") +
                                    ((zones[9]) ? "12," : "") +
                                    ((zones[10]) ? "13,14," : "") +
                                    ((zones[11]) ? "17,18," : "")));
                            if (textName.getText().isEmpty())
                                JOptionPane.showConfirmDialog(infoPanel, "Введите название брони (Name) !", "!", JOptionPane.DEFAULT_OPTION);
                            else if (textDR.getText().isEmpty())
                                JOptionPane.showConfirmDialog(infoPanel, "Введите сопротивление брони! (DR)", "!", JOptionPane.DEFAULT_OPTION);
                            else if (zonesOut.isEmpty())
                                JOptionPane.showConfirmDialog(infoPanel, "Введите зоны, которые защищает броня! (Zones)", "!", JOptionPane.DEFAULT_OPTION);
                            else if (textWeight.getText().isEmpty())
                                JOptionPane.showConfirmDialog(infoPanel, "Введите вес брони! (Weight)", "!", JOptionPane.DEFAULT_OPTION);
                            else if (DBConnect.getArmorOnName(textName.getText()).equals("null") || (textName.getText().equals(oldName) && edit))
                            {
                                if (!edit)
                                {
                                    zonesOut = zonesOut.substring(0, zonesOut.length() - 1);
                                    DBConnect.addNewArmor(textName.getText(),
                                            textDR.getText(),
                                            zonesOut,
                                            (textCost.getText().isEmpty() ? "0" : textCost.getText()),//
                                            textWeight.getText(),
                                            textDescription.getText());//
                                    can = true;
                                }
                                else
                                {
                                    zonesOut = zonesOut.substring(0, zonesOut.length() - 1);
                                    DBConnect.updateArmor(oldName,
                                            textName.getText(),
                                            textDR.getText(),
                                            zonesOut,
                                            (textCost.getText().isEmpty() ? "0" : textCost.getText()),//
                                            textWeight.getText(),
                                            textDescription.getText());//
                                    can = true;
                                }
                            }
                             else
                            {
                                JOptionPane.showConfirmDialog(infoPanel, "Броня с таким именем уже существует!", "!", JOptionPane.DEFAULT_OPTION);
                                can = false;
                            }
                        } catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                        if (can)
                        {
                            dialogChoice.dispose();
                            if (edit)
                                try
                                {
                                    installArmor(DBConnect.getCharacterArmor(Window.characterId));
                                } catch (SQLException e1)
                                {
                                    e1.printStackTrace();
                                }
                            createDialog();
                        }

                    }
                });
                c.gridwidth = 2;
                c.gridx = 1;
                c.gridy = 5;
                gbl.setConstraints(add, c);
                infoPanel.add(add);
            }
        };
        buttonAddNew.setFont(Resources.font15);
        buttonAddNew.setToolTipText("Создать новую броню");
        buttonAddNew.addActionListener(listener);
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonAddNew, c);
        dialogChoice.add(buttonAddNew);
//------------------buttonAddNew-----------------------
//------------------buttonDelete-----------------------
        buttonDelete.setFont(Resources.font15);
        buttonDelete.setToolTipText("Удалить выбранную броню");
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!armorList.isSelectionEmpty())
                {
                    if (JOptionPane.showConfirmDialog(dialogChoice, "Вы уверены, что хотите безвозвратно удалить данные о " + armorList.getSelectedValue() + "?", "!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
                    {
                        try
                        {
                            DBConnect.deleteArmorOnName(armorList.getSelectedValue());
                            installArmor(DBConnect.getCharacterArmor(Window.characterId));
                            dialogChoice.dispose();
                            createDialog();
                        }catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                    }

                }
            }
        });
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonDelete, c);
        dialogChoice.add(buttonDelete);
//------------------buttonDelete-----------------------
//------------------buttonEdit-----------------------
        buttonEdit.setFont(Resources.font15);
        buttonEdit.setToolTipText("Редактировать выбранную броню");
        buttonEdit.addActionListener(listener);
        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonEdit, c);
        dialogChoice.add(buttonEdit);
//------------------buttonEdit-----------------------
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

    void installArmor (Object[][] characterArmor)
    {
        DefaultTableModel dtm = (DefaultTableModel) tableArmor.getModel();
        dtm.setRowCount(0);
        for (Object[] aCharacterArmor : characterArmor)
            dtm.addRow(aCharacterArmor);
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
