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

public class RangedWeaponPanel extends JPanel
{
    private final JTable tableRangedWeapon;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    RangedWeaponPanel() throws SQLException {
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
        JLabel labelTitle = new JLabel("RangedWeapon");
        labelTitle.setFont(Resources.font21);
        labelTitle.setAlignmentX(LEFT_ALIGNMENT);
        labelTitle.setToolTipText("Оружие дальнего боя");
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
                if (tableRangedWeapon.getSelectedRowCount() != 0)
                {
                    Window.isChanged = true;
                    ((DefaultTableModel) tableRangedWeapon.getModel()).removeRow(tableRangedWeapon.getSelectedRow());
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
                if (tableRangedWeapon.getSelectedRowCount() != 0)
                {
                    JDialog dialog = new JDialog();
                    dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

                    dialog.setTitle(String.valueOf(tableRangedWeapon.getValueAt(tableRangedWeapon.getSelectedRow(), 0)));

                    dialog.setModal(false);
                    dialog.setFont(Resources.font31);
                    dialog.setResizable(true);
                    dialog.setSize(700, 400);
                    dialog.setLocation(((screenSize.width / 2) - (dialog.getWidth() / 2)),
                            ((screenSize.height / 2) - (dialog.getHeight() / 2)));
                    JTextArea textDescription = new JTextArea("Error");
                    try
                    {
                        textDescription.setText(DBConnect.getRangedWeaponOnName(dialog.getTitle()));
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
//------------------tableRangedWeapon-----------------------
        tableRangedWeapon = new JTable();
        tableRangedWeapon.setFont(Resources.font15);
        tableRangedWeapon.setSelectionBackground(Resources.GLASS_GREEN);
        tableRangedWeapon.setSelectionForeground(Color.BLACK);
        TableModel model = new DefaultTableModel(DBConnect.getCharacterRangedWeapon(Window.characterId),
                new Object[]{"Ranged Weapon", "Damage", "Damage type", "SS", "Acc", "Range", "Max Range", "RoF", "Shots", "Min ST", "Rcl",  "Cost", "Weight"}){
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex > 11) return Float.class;
                if (columnIndex > 2) return Integer.class;
                return super.getColumnClass(columnIndex);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex)
            {
                return false;
            }
        };
        tableRangedWeapon.setModel(model);
        tableRangedWeapon.setRowHeight(25);
        tableRangedWeapon.getColumnModel().getColumn(1).setMaxWidth(200);
        tableRangedWeapon.getColumnModel().getColumn(2).setMaxWidth(200);
        tableRangedWeapon.getColumnModel().getColumn(3).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(4).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(5).setMaxWidth(100);
        tableRangedWeapon.getColumnModel().getColumn(6).setMaxWidth(100);
        tableRangedWeapon.getColumnModel().getColumn(7).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(8).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(9).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(10).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(11).setMaxWidth(50);
        tableRangedWeapon.getColumnModel().getColumn(12).setMaxWidth(50);
        tableRangedWeapon.getTableHeader().setReorderingAllowed(false);
        tableRangedWeapon.setRowSorter(new TableRowSorter<>(tableRangedWeapon.getModel()));

        c.fill = GridBagConstraints.BOTH;
        c.gridwidth  = GridBagConstraints.REMAINDER;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(15, 20, 0, 20);

        gbl.setConstraints(tableRangedWeapon.getTableHeader(), c);
        add(tableRangedWeapon.getTableHeader());
        c.gridheight = GridBagConstraints.RELATIVE;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 1;
        c.insets = new Insets(0, 20, 15, 20);
        gbl.setConstraints(tableRangedWeapon, c);
        add(tableRangedWeapon);

//------------------tableRangedWeapon-----------------------

    }


    private void createDialog()
    {
        JDialog dialogChoice = new JDialog();
        dialogChoice.setTitle("Choice ranged weapon");
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
        String[][] rangedWeapon = new String[0][];
        try {
            rangedWeapon = DBConnect.getAllRangedWeapon();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        DefaultListModel<String> listModel = new DefaultListModel<>();

        for (String s: rangedWeapon[0])
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
        labelDamage.setText("Damage: " + rangedWeapon[1][skillsList.getSelectedIndex()]);
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
        labelType.setText("Damage type: " + rangedWeapon[2][skillsList.getSelectedIndex()]);
        labelType.setFont(Resources.font15);
        labelType.setToolTipText("Тип урона");
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelType, c);
        infoPanel.add(labelType);
//------------------labelType-------------------------
//------------------labelSS-------------------------
        JLabel labelSS = new JLabel();
        labelSS.setText("SS: " + rangedWeapon[3][skillsList.getSelectedIndex()]);
        labelSS.setFont(Resources.font15);
        labelSS.setToolTipText("Значение для выстрела навскидку");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelSS, c);
        infoPanel.add(labelSS);
//------------------labelSS-------------------------
//------------------labelAcc-------------------------
        JLabel labelAcc = new JLabel();
        labelAcc.setText("Acc: " + rangedWeapon[4][skillsList.getSelectedIndex()]);
        labelAcc.setFont(Resources.font15);
        labelAcc.setToolTipText("Бонус за прицеливание");
        c.gridx = 2;
        c.gridy = 2;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelAcc, c);
        infoPanel.add(labelAcc);
//------------------labelAcc-------------------------
//------------------labelRange-------------------------
        JLabel labelRange = new JLabel();
        labelRange.setText("Range: " + rangedWeapon[5][skillsList.getSelectedIndex()]);
        labelRange.setFont(Resources.font15);
        labelRange.setToolTipText("Эффективная дистанция стрельбы");
        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelRange, c);
        infoPanel.add(labelRange);
//------------------labelRange-------------------------
//------------------labelMaxRange-------------------------
        JLabel labelMaxRange = new JLabel();
        labelMaxRange.setText("Max range: " + rangedWeapon[6][skillsList.getSelectedIndex()]);
        labelMaxRange.setFont(Resources.font15);
        labelMaxRange.setToolTipText("Максимальная дистанция стрельбы");
        c.gridx = 4;
        c.gridy = 2;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelMaxRange, c);
        infoPanel.add(labelMaxRange);
//------------------labelMaxRange-------------------------
//------------------labelRoF-------------------------
        JLabel labelRoF = new JLabel();
        labelRoF.setText("Rof: " + rangedWeapon[7][skillsList.getSelectedIndex()]);
        labelRoF.setFont(Resources.font15);
        labelRoF.setToolTipText("Количество выстрелов в секунду");
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelRoF, c);
        infoPanel.add(labelRoF);
//------------------labelRoF-------------------------
//------------------labelShots-------------------------
        JLabel labelShots = new JLabel();
        labelShots.setText("Shots: " + rangedWeapon[8][skillsList.getSelectedIndex()]);
        labelShots.setFont(Resources.font15);
        labelShots.setToolTipText("Количество патронов в одном магазине/обойме/ленте...");
        c.gridx = 2;
        c.gridy = 3;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelShots, c);
        infoPanel.add(labelShots);
//------------------labelShots-------------------------
// ------------------labelMinST-------------------------
        JLabel labelMinST = new JLabel();
        labelMinST.setText("Min ST: " + rangedWeapon[9][skillsList.getSelectedIndex()]);
        labelMinST.setFont(Resources.font15);
        labelMinST.setToolTipText("Минимальная сила для испольнования");
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 3;
        c.gridy = 3;
        c.weightx = 0.0;
        c.weighty = 0.0;
        gbl.setConstraints(labelMinST, c);
        infoPanel.add(labelMinST);
//------------------labelMinST-------------------------
//------------------labelRcl-------------------------
        JLabel labelRcl = new JLabel();
        labelRcl.setText("Rcl: " + rangedWeapon[10][skillsList.getSelectedIndex()]);
        labelRcl.setFont(Resources.font15);
        labelRcl.setToolTipText("Время перезарядки");
        c.gridx = 4;
        c.gridy = 3;
        c.weightx = 1;
        c.weighty = 0.0;
        gbl.setConstraints(labelRcl, c);
        infoPanel.add(labelRcl);
//------------------labelType-------------------------
//------------------labelWeight-------------------------
        JLabel labelWeight = new JLabel();
        labelWeight.setText("Weight: " + rangedWeapon[12][skillsList.getSelectedIndex()]);
        labelWeight.setFont(Resources.font15);
        labelWeight.setToolTipText("Вес оружия");
        c.gridx = 1;
        c.gridy = 4;
        c.weightx = 0.0;
        gbl.setConstraints(labelWeight, c);
        infoPanel.add(labelWeight);
//------------------labelWeight-------------------------
//------------------labelCost-------------------------
        JLabel labelCost = new JLabel();
        labelCost.setText("Cost: " + ((rangedWeapon[11][skillsList.getSelectedIndex()])));
        labelCost.setFont(Resources.font15);
        labelCost.setToolTipText("Средняя стоимость оружия");
        c.gridx = 2;
        c.gridy = 4;
        gbl.setConstraints(labelCost, c);
        infoPanel.add(labelCost);
//------------------labelCost-------------------------
//------------------textDescription-----------------------
        JTextArea textDescription = new JTextArea();
        textDescription.setText(rangedWeapon[13][skillsList.getSelectedIndex()]);
        textDescription.setFont(Resources.font11);
        textDescription.setLineWrap(true);
        textDescription.setEditable(false);
        textDescription.setBackground(Color.lightGray);
        c.gridx = 1;
        c.gridy = 5;
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
        String[][] finalArmor = rangedWeapon;
        skillsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                labelDamage.setText("Damage: " + finalArmor[1][skillsList.getSelectedIndex()]);
                labelType.setText("Damage type: " + finalArmor[2][skillsList.getSelectedIndex()]);
                labelSS.setText("SS: " + finalArmor[3][skillsList.getSelectedIndex()]);
                labelAcc.setText("Acc: " + finalArmor[4][skillsList.getSelectedIndex()]);
                labelRange.setText("Range: " + finalArmor[5][skillsList.getSelectedIndex()]);
                labelMaxRange.setText("Max range: " + finalArmor[6][skillsList.getSelectedIndex()]);
                labelRoF.setText("RoF: " + finalArmor[7][skillsList.getSelectedIndex()]);
                labelShots.setText("Shots: " + finalArmor[8][skillsList.getSelectedIndex()]);
                labelMinST.setText("Min ST: " + finalArmor[9][skillsList.getSelectedIndex()]);
                labelRcl.setText("Rcl: " + finalArmor[10][skillsList.getSelectedIndex()]);
                labelWeight.setText("Weight: " + finalArmor[12][skillsList.getSelectedIndex()]);
                labelCost.setText("Cost: " + (finalArmor[11][skillsList.getSelectedIndex()]));
                textDescription.setText(finalArmor[13][skillsList.getSelectedIndex()]);
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
                dtm = (DefaultTableModel) tableRangedWeapon.getModel();
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
                            Integer.parseInt(labelSS.getText().substring(4)),
                            Integer.parseInt(labelAcc.getText().substring(5)),
                            Integer.parseInt(labelRange.getText().substring(7)),
                            Integer.parseInt(labelMaxRange.getText().substring(11)),
                            Integer.parseInt(labelRoF.getText().substring(5)),
                            Integer.parseInt(labelShots.getText().substring(7)),
                            Integer.parseInt(labelMinST.getText().substring(8)),
                            Integer.parseInt(labelRcl.getText().substring(5)),
                            Integer.parseInt(labelCost.getText().substring(6)),
                            Float.parseFloat(labelWeight.getText().substring(8))});
                    Window.isChanged = true;
                    dialogChoice.dispose();
                }
                Window.mathPoints();
            }
        });
        c.gridx = 1;
        c.gridy = 6;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.gridwidth = 1;
        c.gridheight = 1;
        gbl.setConstraints(buttonAdd, c);
        infoPanel.add(buttonAdd);
//------------------buttonAdd-----------------------
//------------------buttonAddNew-----------------------
        JButton buttonAddNew = new JButton("Add new ranged weapon...");
        buttonAddNew.setFont(Resources.font15);
        buttonAddNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String dmg = labelDamage.getText();
                String type = labelType.getText();
                String ss = labelSS.getText();
                String acc = labelAcc.getText();
                String range = labelRange.getText();
                String maxrange = labelMaxRange.getText();
                String rof = labelRoF.getText();
                String shots = labelShots.getText();
                String minst = labelMinST.getText();
                String rcl = labelRcl.getText();
                String cost = labelCost.getText();
                String wt = labelWeight.getText();
                textDescription.setEditable(true);
                textDescription.setBackground(Color.WHITE);
                buttonAdd.setVisible(false);
                buttonAddNew.setVisible(false);
                scrollPane.setVisible(false);
                JTextField textCost = new JTextField();
                ((AbstractDocument) textCost.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textMinST = new JTextField();
                ((AbstractDocument) textMinST.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textSS = new JTextField();
                ((AbstractDocument) textSS.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textAcc = new JTextField();
                ((AbstractDocument) textAcc.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textRoF = new JTextField();
                ((AbstractDocument) textRoF.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textShots = new JTextField();
                ((AbstractDocument) textShots.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textRange = new JTextField();
                ((AbstractDocument) textRange.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textMaxRange = new JTextField();
                ((AbstractDocument) textMaxRange.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textRcl = new JTextField();
                ((AbstractDocument) textRcl.getDocument()).setDocumentFilter(new IntDocumentFilter(false));
                JTextField textWeight = new JTextField();
                ((AbstractDocument) textWeight.getDocument()).setDocumentFilter(new FloatDocumentFilter(textWeight, false));
                JTextField textDamage = new JTextField();
                JMenuBar menuBarDamageType = new JMenuBar();
                menuBarDamageType.setFont(Resources.font15);
                Boolean [] damageType = new Boolean[]{false,false,false};
                JMenu menuDamageType = new JMenu("Damage type");
                menuDamageType.setSelected(true);
                JRadioButtonMenuItem rb1 = new JRadioButtonMenuItem("Рубящий",false);
                rb1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        damageType[0] = rb1.isArmed();
                    }
                });
                menuDamageType.add(rb1);
                JRadioButtonMenuItem rb2 = new JRadioButtonMenuItem("Проникающий",false);
                rb2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        damageType[1] = rb2.isArmed();
                    }
                });
                menuDamageType.add(rb2);
                JRadioButtonMenuItem rb3 = new JRadioButtonMenuItem("Дробящий",false);
                rb3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        damageType[2] = rb3.isArmed();
                    }
                });
                menuDamageType.add(rb3);
                menuBarDamageType.add(menuDamageType);

                c.gridwidth  = 1;
                c.gridheight = 1;
                c.gridx = 1;
                c.gridy = 2;
                c.weightx = 0;
                c.weighty = 0;
                labelDamage.setText("Damage:");
                gbl.setConstraints(labelDamage, c);

                c.weightx = 1;
                c.gridx = 2;
                textDamage.setFont(Resources.font15);
                gbl.setConstraints(textDamage, c);
                infoPanel.add(textDamage);

                c.weightx = 0;
                c.gridx = 3;
                labelType.setText("Damage type:");
                gbl.setConstraints(labelType, c);

                c.weightx = 1;
                c.gridx = 4;
                menuBarDamageType.setFont(Resources.font15);
                gbl.setConstraints(menuBarDamageType, c);
                infoPanel.add(menuBarDamageType);

                c.weightx = 0;
                c.gridx = 1;
                c.gridy = 3;
                labelSS.setText("SS:");
                gbl.setConstraints(labelSS, c);

                c.weightx = 1;
                c.gridx = 2;
                textSS.setFont(Resources.font15);
                gbl.setConstraints(textSS, c);
                infoPanel.add(textSS);

                c.weightx = 0;
                c.gridx = 3;
                labelAcc.setText("Acc:");
                gbl.setConstraints(labelAcc, c);

                c.weightx = 1;
                c.gridx = 4;
                textAcc.setFont(Resources.font15);
                gbl.setConstraints(textAcc, c);
                infoPanel.add(textAcc);

                c.weightx = 0;
                c.gridx = 1;
                c.gridy = 4;
                labelRoF.setText("RoF:");
                gbl.setConstraints(labelRoF, c);

                c.weightx = 1;
                c.gridx = 2;
                textRoF.setFont(Resources.font15);
                gbl.setConstraints(textRoF, c);
                infoPanel.add(textRoF);

                c.weightx = 0;
                c.gridx = 3;
                labelShots.setText("Shots:");
                gbl.setConstraints(labelShots, c);

                c.weightx = 1;
                c.gridx = 4;
                textShots.setFont(Resources.font15);
                gbl.setConstraints(textShots, c);
                infoPanel.add(textShots);

                c.weightx = 0;
                c.gridy = 5;
                c.gridx = 1;
                labelRange.setText("Range:");
                gbl.setConstraints(labelRange, c);

                c.weightx = 1;
                c.gridx = 2;
                textRange.setFont(Resources.font15);
                gbl.setConstraints(textRange, c);
                infoPanel.add(textRange);

                c.weightx = 0;
                c.gridx = 3;
                labelMaxRange.setText("Max range:");
                gbl.setConstraints(labelMaxRange, c);

                c.weightx = 1;
                c.gridx = 4;
                textMaxRange.setFont(Resources.font15);
                gbl.setConstraints(textMaxRange, c);
                infoPanel.add(textMaxRange);

                c.weightx = 0;
                c.gridy = 6;
                c.gridx = 1;
                labelMinST.setText("Min ST:");
                gbl.setConstraints(labelMinST, c);

                c.weightx = 1;
                c.gridx = 2;
                textMinST.setFont(Resources.font15);
                gbl.setConstraints(textMinST, c);
                infoPanel.add(textMinST);

                c.weightx = 0;
                c.gridx = 3;
                labelRcl.setText("Rcl:");
                gbl.setConstraints(labelRcl, c);

                c.weightx = 1;
                c.gridx = 4;
                textRcl.setFont(Resources.font15);
                gbl.setConstraints(textRcl, c);
                infoPanel.add(textRcl);

                c.weightx = 0;
                c.gridx = 1;
                c.gridy = 7;
                labelWeight.setText("Weight:");
                gbl.setConstraints(labelWeight, c);

                c.weightx = 1;
                c.gridx = 2;
                textWeight.setFont(Resources.font15);
                gbl.setConstraints(textWeight, c);
                infoPanel.add(textWeight);

                c.weightx = 0;
                c.gridx = 3;
                labelCost.setText("Cost:");
                gbl.setConstraints(labelCost, c);

                c.weightx = 1;
                c.gridx = 4;
                textCost.setFont(Resources.font15);
                gbl.setConstraints(textCost, c);
                infoPanel.add(textCost);

                c.gridx = 1;
                c.gridy = 8;
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
                add.setText("Add new ranged weapon");

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
                        infoPanel.remove(textAcc);
                        infoPanel.remove(textSS);
                        infoPanel.remove(textRoF);
                        infoPanel.remove(textShots);
                        infoPanel.remove(textRange);
                        infoPanel.remove(textMaxRange);
                        infoPanel.remove(textRcl);
                        infoPanel.remove(textMinST);
                        infoPanel.remove(textWeight);
                        infoPanel.remove(menuBarDamageType);
                        infoPanel.remove(cancel);
                        infoPanel.remove(add);
                        textDescription.setEditable(false);
                        textDescription.setBackground(Color.LIGHT_GRAY);
                        buttonAdd.setVisible(true);
                        buttonAddNew.setVisible(true);
                        scrollPane.setVisible(true);
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
                        labelSS.setText(ss);
                        gbl.setConstraints(labelSS, c);
                        c.gridx = 2;
                        labelAcc.setText(acc);
                        gbl.setConstraints(labelAcc, c);
                        c.gridx = 3;
                        labelRange.setText(range);
                        gbl.setConstraints(labelRange, c);
                        c.gridx = 4;
                        labelMaxRange.setText(maxrange);
                        gbl.setConstraints(labelMaxRange, c);
                        c.gridx = 1;
                        c.gridy = 3;
                        labelRoF.setText(rof);
                        gbl.setConstraints(labelRoF, c);
                        c.gridx = 2;
                        labelShots.setText(shots);
                        gbl.setConstraints(labelShots, c);
                        c.gridx = 3;
                        labelMinST.setText(minst);
                        gbl.setConstraints(labelMinST, c);
                        c.gridx = 4;
                        labelRcl.setText(rcl);
                        gbl.setConstraints(labelRcl, c);
                        c.gridx = 1;
                        c.gridy = 4;
                        labelWeight.setText(wt);
                        gbl.setConstraints(labelWeight, c);
                        c.gridx = 2;
                        labelCost.setText(cost);
                        gbl.setConstraints(labelCost, c);
                        c.gridx = 1;
                        c.gridy = 5;
                        c.weightx = 1;
                        c.weighty = 1;
                        c.gridwidth = GridBagConstraints.REMAINDER;
                        c.gridheight = GridBagConstraints.RELATIVE;
                        try
                        {
                            textDescription.setText(DBConnect.getRangedWeaponOnName(skillsList.getSelectedValue()));
                        } catch (SQLException e1)
                        {
                            e1.printStackTrace();
                        }
                        gbl.setConstraints(scrollDescription, c);
                    }
                });
                c.gridwidth = 2;
                c.gridx = 3;
                c.gridy = 9;
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
                            String dmgTypeOut = (((damageType[0]) ? "реж," : "") +
                                    ((damageType[1]) ? "прон," : "") +
                                    ((damageType[2]) ? "дроб," : ""));
                            dmgTypeOut = dmgTypeOut.substring(0,dmgTypeOut.length()-1);

                            if (DBConnect.getRangedWeaponOnName(textName.getText()).equals("null"))
                                DBConnect.addNewRangedWeapon(textName.getText(),
                                        textDamage.getText(),
                                        dmgTypeOut,
                                        textSS.getText(),
                                        textAcc.getText(),
                                        textRange.getText(),
                                        textMaxRange.getText(),
                                        textRoF.getText(),
                                        textShots.getText(),
                                        textMinST.getText(),
                                        textRcl.getText(),
                                        textCost.getText(),
                                        textWeight.getText(),
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
                c.gridy = 9;
                gbl.setConstraints(add, c);
                infoPanel.add(add);
            }
        });
        c.gridx = 2;
        c.gridy = 6;
        gbl.setConstraints(buttonAddNew, c);
        infoPanel.add(buttonAddNew);
//------------------buttonAddNew-----------------------
        dialogChoice.setVisible(true);
    }


    void clear()
    {
        DefaultTableModel dtm = (DefaultTableModel) tableRangedWeapon.getModel();
        dtm.setRowCount(0);
    }

    void saveStats()
    {
        String[] name = new String[tableRangedWeapon.getRowCount()];

        for (int i = 0; i < tableRangedWeapon.getRowCount(); i++)
        {
            name[i] = tableRangedWeapon.getValueAt(i, 0).toString();
        }
        try
        {
            DBConnect.saveRangedWeapon(name, Window.characterId);
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

        g.drawLine(5, tableRangedWeapon.getTableHeader().getY() - 5,
                getWidth()-5, tableRangedWeapon.getTableHeader().getY() - 5);

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


    public JTable getTableRangedWeapon() {
        return tableRangedWeapon;
    }

}
