package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLOutput;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

class BaseStatPanel extends JPanel
{
    private JLabel labelHandDamageSwCount;
    private JLabel labelHandDamageThrCount;
    private JLabel labelBMCount;
    private JLabel labelBLCount;
    private JLabel labelBSCount;
    private JLabel labelSTCount;
    private JLabel labelDXCount;
    private JLabel labelIQCount;
    private JLabel labelHTCount;
    private JLabel labelHPCount;
    private JLabel labelWillCount;
    private JLabel labelPerCount;
    private JLabel labelFPCount;
    
    BaseStatPanel ()
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
        c.insets = new Insets(0, 10, 0, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        setBackground(Color.GREEN);
//===========================Labels===============================        
//------------------labelST-----------------------
        JLabel labelST = new JLabel("ST");
        labelST.setFont(Resources.font15B);
        labelST.setToolTipText("Сила");
        c.gridx = 1;
        c.gridy = 1;
        gbl.setConstraints(labelST, c);
        add(labelST);
//------------------labelST-----------------------
//------------------labelSTCount-----------------------
        labelSTCount = new JLabel("10");
        labelSTCount.setFont(Resources.font15);
        c.gridx = 2;
        c.gridy = 1;
        gbl.setConstraints(labelSTCount, c);
        add(labelSTCount);
//------------------labelSTCount-----------------------
//------------------labelHP-----------------------
        JLabel labelHP = new JLabel("HP");
        labelHP.setFont(Resources.font15B);
        labelHP.setToolTipText("Количество хит поинтов");
        c.gridx = 5;
        c.gridy = 1;
        gbl.setConstraints(labelHP, c);
        add(labelHP);
//------------------labelHP-----------------------
//------------------labelHPCount-----------------------
        labelHPCount = new JLabel("10");
        labelHPCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 1;
        gbl.setConstraints(labelHPCount, c);
        add(labelHPCount);
//------------------labelHTCount-----------------------
//------------------labelDX-----------------------
        JLabel labelDX = new JLabel("DX");
        labelDX.setFont(Resources.font15B);
        labelDX.setToolTipText("Ловкость");
        c.gridx = 1;
        c.gridy = 2;
        gbl.setConstraints(labelDX, c);
        add(labelDX);
//------------------labelDX-----------------------
//------------------labelDXCount-----------------------
        labelDXCount = new JLabel("10");
        labelDXCount.setFont(Resources.font15);
        c.gridx = 2;
        c.gridy = 2;
        gbl.setConstraints(labelDXCount, c);
        add(labelDXCount);
//------------------labelDXCount-----------------------
//------------------labelWill-----------------------
        JLabel labelWill = new JLabel("Will");
        labelWill.setFont(Resources.font15B);
        labelWill.setToolTipText("Воля");
        c.gridx = 5;
        c.gridy = 2;
        gbl.setConstraints(labelWill, c);
        add(labelWill);
//------------------labelWill-----------------------
//------------------labelWillCount-----------------------
        labelWillCount = new JLabel("10");
        labelWillCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 2;
        gbl.setConstraints(labelWillCount, c);
        add(labelWillCount);
//------------------labelWillCount-----------------------
//------------------labelIQ-----------------------
        JLabel labelIQ = new JLabel("IQ");
        labelIQ.setFont(Resources.font15B);
        labelIQ.setToolTipText("Интелект");
        c.gridx = 1;
        c.gridy = 3;
        gbl.setConstraints(labelIQ, c);
        add(labelIQ);
//------------------labelIQ-----------------------
//------------------labelIQCount-----------------------
        labelIQCount = new JLabel("10");
        labelIQCount.setFont(Resources.font15);
        c.gridx = 2;
        c.gridy = 3;
        gbl.setConstraints(labelIQCount, c);
        add(labelIQCount);
//------------------labelIQCount-----------------------
//------------------labelPer-----------------------
        JLabel labelPer = new JLabel("Per");
        labelPer.setFont(Resources.font15B);
        labelPer.setToolTipText("Восприятие");
        c.gridx = 5;
        c.gridy = 3;
        gbl.setConstraints(labelPer, c);
        add(labelPer);
//------------------labelPer-----------------------
//------------------labelPerCount-----------------------
        labelPerCount = new JLabel("10");
        labelPerCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 3;
        gbl.setConstraints(labelPerCount, c);
        add(labelPerCount);
//------------------labelPerCount-----------------------
//------------------labelHT-----------------------
        JLabel labelHT = new JLabel("HT");
        labelHT.setFont(Resources.font15B);
        labelHT.setToolTipText("Здоровье");
        c.gridx = 1;
        c.gridy = 4;
        gbl.setConstraints(labelHT, c);
        add(labelHT);
//------------------labelHT----------------------
//------------------labelHTCount-----------------------
        labelHTCount = new JLabel("10");
        labelHTCount.setFont(Resources.font15);
        c.gridx = 2;
        c.gridy = 4;
        gbl.setConstraints(labelHTCount, c);
        add(labelHTCount);
//------------------labelHTCount-----------------------
//------------------labelFP-----------------------
        JLabel labelFP = new JLabel("FP");
        labelFP.setFont(Resources.font15B);
        labelFP.setToolTipText("Выносливость");
        c.gridx = 5;
        c.gridy = 4;
        gbl.setConstraints(labelFP, c);
        add(labelFP);
//------------------labelFP-----------------------
//------------------labelFPCount-----------------------
        labelFPCount = new JLabel("10");
        labelFPCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 4;
        gbl.setConstraints(labelFPCount, c);
        add(labelFPCount);
//------------------labelFPCount-----------------------
//------------------labelBasicSpeed-----------------------
        JLabel labelBasicSpeed = new JLabel("BS");
        labelBasicSpeed.setFont(Resources.font15B);
        labelBasicSpeed.setToolTipText("Базовая скорость");
        c.insets = new Insets(10, 10, 0, 10);
        c.gridx = 1;
        c.gridy = 5;
        gbl.setConstraints(labelBasicSpeed, c);
        add(labelBasicSpeed);
//------------------labelBasicSpeed-----------------------
//------------------labelBSCount-----------------------
        labelBSCount = new JLabel(String.valueOf((Integer.parseInt(labelDXCount.getText())+Integer.parseInt(labelHTCount.getText()))/4.0));
        labelBSCount.setFont(Resources.font15);
        c.gridx = 2;
        c.gridy = 5;
        gbl.setConstraints(labelBSCount, c);
        add(labelBSCount);
//------------------labelBasicSpeed-----------------------
//------------------labelBasicLift-----------------------
        JLabel labelBasicLift = new JLabel("BL");
        labelBasicLift.setFont(Resources.font15B);
        labelBasicLift.setToolTipText("Базовая нагрузка");
        c.gridx = 5;
        c.gridy = 5;
        gbl.setConstraints(labelBasicLift, c);
        add(labelBasicLift);
//------------------labelBasicLift-----------------------
//------------------labelBLCount-----------------------
        labelBLCount = new JLabel(String.valueOf((Integer.parseInt((labelHTCount.getText())) * (Integer.parseInt(labelHTCount.getText())))/5));
        labelBLCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 5;
        gbl.setConstraints(labelBLCount, c);
        add(labelBLCount);
//------------------labelBLCount-----------------------
//------------------labelBasicMove-----------------------
        JLabel labelBasicMove = new JLabel("BM");
        labelBasicMove.setFont(Resources.font15B);
        labelBasicMove.setToolTipText("Базовое перемещение");
        c.insets = new Insets(0, 10, 0, 10);
        c.gridx = 1;
        c.gridy = 6;
        gbl.setConstraints(labelBasicMove, c);
        add(labelBasicMove);
//------------------labelBasicMove-----------------------
//------------------labelBMCount-----------------------
        labelBMCount = new JLabel(String.valueOf((int)(Float.parseFloat(labelBSCount.getText()))));
        labelBMCount.setFont(Resources.font15);
        c.gridx = 2;
        c.gridy = 6;
        gbl.setConstraints(labelBMCount, c);
        add(labelBMCount);
//------------------labelBasicSpeed-----------------------
//------------------labelHandDamage-----------------------
        JLabel labelHandDamage = new JLabel("Hand Damage");
        labelHandDamage.setFont(Resources.font15B);
        labelHandDamage.setToolTipText("Базовый урон");
        c.gridx = 5;
        c.gridy = 6;
        c.gridwidth = 3;
        c.insets = new Insets(10, 10, 0, 10);
        gbl.setConstraints(labelHandDamage, c);
        add(labelHandDamage);
//------------------labelHandDamage-----------------------
//------------------labelHandDamageThr-----------------------
        JLabel labelHandDamageThr = new JLabel("Thr");
        labelHandDamageThr.setFont(Resources.font15B);
        labelHandDamageThr.setToolTipText("Колющий удар");
        c.gridx = 5;
        c.gridy = 7;
        c.insets = new Insets(0, 10, 0, 10);
        gbl.setConstraints(labelHandDamageThr, c);
        add(labelHandDamageThr);
//------------------labelHandDamageThr-----------------------
//------------------labelHandDamageThrCount-----------------------
        labelHandDamageThrCount = new JLabel("1d-2");
        labelHandDamageThrCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 7;
        c.gridwidth = 2;
        gbl.setConstraints(labelHandDamageThrCount, c);
        add(labelHandDamageThrCount);
//------------------labelHandDamageThr-----------------------
//------------------labelHandDamageSw-----------------------
        JLabel labelHandDamageSw = new JLabel("Sw");
        labelHandDamageSw.setFont(Resources.font15B);
        labelHandDamageSw.setToolTipText("Рубящий удар");
        c.gridx = 5;
        c.gridy = 8;
        c.gridwidth = 1;
        gbl.setConstraints(labelHandDamageSw, c);
        add(labelHandDamageSw);
//------------------labelHandDamageThr-----------------------
//------------------labelHandDamageSwCount-----------------------
        labelHandDamageSwCount = new JLabel("1d");
        labelHandDamageSwCount.setFont(Resources.font15);
        c.gridx = 6;
        c.gridy = 8;
        c.gridwidth = 2;
        gbl.setConstraints(labelHandDamageSwCount, c);
        add(labelHandDamageSwCount);
//------------------labelHandDamageThrCount-----------------------
//===========================Labels===============================
//===========================Buttons===============================
//-------------------buttonPlusST------------------------
        BaseStatPanel.ButtonCount buttonPlusST = new BaseStatPanel.ButtonCount("ST", true);
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        gbl.setConstraints(buttonPlusST, c);
        add(buttonPlusST);
//-------------------buttonPlusST------------------------
//-------------------buttonMinusST------------------------
        BaseStatPanel.ButtonCount buttonMinusST = new BaseStatPanel.ButtonCount("ST", false);
        c.gridx = 4;
        c.gridy = 1;
        c.weightx = 1;
        gbl.setConstraints(buttonMinusST, c);
        add(buttonMinusST);
//-------------------buttonMinusST------------------------
//-------------------buttonPlusDX------------------------
        BaseStatPanel.ButtonCount buttonPlusDX = new BaseStatPanel.ButtonCount("DX", true);
        c.gridx = 3;
        c.gridy = 2;
        c.weightx = 0.0;
        gbl.setConstraints(buttonPlusDX, c);
        add(buttonPlusDX);
//-------------------buttonPlusDX------------------------
//-------------------buttonMinusDX------------------------
        BaseStatPanel.ButtonCount buttonMinusDX = new BaseStatPanel.ButtonCount("DX", false);
        c.gridx = 4;
        c.gridy = 2;
        gbl.setConstraints(buttonMinusDX, c);
        add(buttonMinusDX);
//-------------------buttonMinusDX------------------------
//-------------------buttonPlusIQ------------------------
        BaseStatPanel.ButtonCount buttonPlusIQ = new BaseStatPanel.ButtonCount("IQ", true);
        c.gridx = 3;
        c.gridy = 3;
        gbl.setConstraints(buttonPlusIQ, c);
        add(buttonPlusIQ);
//-------------------buttonPlusIQ------------------------
//-------------------buttonMinusIQ------------------------
        BaseStatPanel.ButtonCount buttonMinusIQ = new BaseStatPanel.ButtonCount("IQ", false);
        c.gridx = 4;
        c.gridy = 3;
        gbl.setConstraints(buttonMinusIQ, c);
        add(buttonMinusIQ);
//-------------------buttonMinusIQ------------------------
//-------------------buttonPlusHT------------------------
        BaseStatPanel.ButtonCount buttonPlusHT = new BaseStatPanel.ButtonCount("HT", true);
        c.gridx = 3;
        c.gridy = 4;
        gbl.setConstraints(buttonPlusHT, c);
        add(buttonPlusHT);
//-------------------buttonPlusHT------------------------
//-------------------buttonMinusHT------------------------
        BaseStatPanel.ButtonCount buttonMinusHT = new BaseStatPanel.ButtonCount("HT", false);
        c.gridx = 4;
        c.gridy = 4;
        gbl.setConstraints(buttonMinusHT, c);
        add(buttonMinusHT);
//-------------------buttonMinusHT------------------------
//-------------------buttonPlusHP------------------------
        BaseStatPanel.ButtonCount buttonPlusHP = new BaseStatPanel.ButtonCount("HP", true);
        c.gridx = 7;
        c.gridy = 1;
        gbl.setConstraints(buttonPlusHP, c);
        add(buttonPlusHP);
//-------------------buttonPlusHP------------------------
//-------------------buttonMinusHP------------------------
        BaseStatPanel.ButtonCount buttonMinusHP = new BaseStatPanel.ButtonCount("HP", false);
        c.gridx = 8;
        c.gridy = 1;
        c.weightx = 1;
        gbl.setConstraints(buttonMinusHP, c);
        add(buttonMinusHP);
//-------------------buttonMinusHP------------------------
//-------------------buttonPlusWill------------------------
        BaseStatPanel.ButtonCount buttonPlusWill = new BaseStatPanel.ButtonCount("Will", true);
        c.gridx = 7;
        c.gridy = 2;
        c.weightx = 0.0;
        gbl.setConstraints(buttonPlusWill, c);
        add(buttonPlusWill);
//-------------------buttonPlusWill------------------------
//-------------------buttonMinusWill------------------------
        BaseStatPanel.ButtonCount buttonMinusWill = new BaseStatPanel.ButtonCount("Will", false);
        c.gridx = 8;
        c.gridy = 2;
        gbl.setConstraints(buttonMinusWill, c);
        add(buttonMinusWill);
//-------------------buttonMinusWill------------------------
//-------------------buttonPlusPer------------------------
        BaseStatPanel.ButtonCount buttonPlusPer = new BaseStatPanel.ButtonCount("Per", true);
        c.gridx = 7;
        c.gridy = 3;
        gbl.setConstraints(buttonPlusPer, c);
        add(buttonPlusPer);
//-------------------buttonPlusPer------------------------
//-------------------buttonMinusPer------------------------
        BaseStatPanel.ButtonCount buttonMinusPer = new BaseStatPanel.ButtonCount("Per", false);
        c.gridx = 8;
        c.gridy = 3;
        gbl.setConstraints(buttonMinusPer, c);
        add(buttonMinusPer);
//-------------------buttonMinusPer------------------------
//-------------------buttonPlusFP------------------------
        BaseStatPanel.ButtonCount buttonPlusFP = new BaseStatPanel.ButtonCount("FP", true);
        c.gridx = 7;
        c.gridy = 4;
        gbl.setConstraints(buttonPlusFP, c);
        add(buttonPlusFP);
//-------------------buttonPlusFP------------------------
//-------------------buttonMinusFP------------------------
        BaseStatPanel.ButtonCount buttonMinusFP = new BaseStatPanel.ButtonCount("FP", false);
        c.gridx = 8;
        c.gridy = 4;
        gbl.setConstraints(buttonMinusFP, c);
        add(buttonMinusFP);
//-------------------buttonMinusFP------------------------
//-------------------buttonPlusBS------------------------
        BaseStatPanel.ButtonCount buttonPlusBS = new BaseStatPanel.ButtonCount("BS", true);
        c.gridx = 3;
        c.gridy = 5;
        c.insets = new Insets(10, 10, 0, 10);
        gbl.setConstraints(buttonPlusBS, c);
        add(buttonPlusBS);
//-------------------buttonPlusBS------------------------
//-------------------buttonMinusBS------------------------
        BaseStatPanel.ButtonCount buttonMinusBS = new BaseStatPanel.ButtonCount("BS", false);
        c.gridx = 4;
        c.gridy = 5;
        gbl.setConstraints(buttonMinusBS, c);
        add(buttonMinusBS);
//-------------------buttonMinusBS------------------------
//-------------------buttonPlusBM------------------------
        BaseStatPanel.ButtonCount buttonPlusBM = new BaseStatPanel.ButtonCount("BM", true);
        c.gridx = 3;
        c.gridy = 6;
        c.insets = new Insets(0, 10, 0, 10);
        gbl.setConstraints(buttonPlusBM, c);
        add(buttonPlusBM);
//-------------------buttonPlusBM------------------------
//-------------------buttonMinusBM------------------------
        BaseStatPanel.ButtonCount buttonMinusBM = new BaseStatPanel.ButtonCount("BM", false);
        c.gridx = 4;
        c.gridy = 6;
        gbl.setConstraints(buttonMinusBM, c);
        add(buttonMinusBM);
//-------------------buttonMinusBM------------------------
//===========================Buttons===============================
    }

    class ButtonCount extends JButton
    {
        ButtonCount(String type, Boolean addOrDec)
        {
            if (addOrDec)
                setText("+");
            else
                setText("-");
            addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    Window.isChanged = true;
                    switch (type)
                    {
                        case "ST":
                            if (addOrDec)
                            {
                                labelHPCount.setText(String.valueOf(Integer.parseInt(labelHPCount.getText()) + 1));
                                labelSTCount.setText(String.valueOf(Integer.parseInt(labelSTCount.getText()) + 1));
                                try
                                {
                                    labelHandDamageThrCount.setText(DBConnect.getThrDamageOnST(Integer.parseInt(labelSTCount.getText())));
                                    labelHandDamageSwCount.setText(DBConnect.getSwDamageOnST(Integer.parseInt(labelSTCount.getText())));
                                } catch (SQLException e1)
                                {
                                    e1.printStackTrace();
                                }
                            }
                            else if (!labelSTCount.getText().equals("1"))
                            {
                                labelHPCount.setText(String.valueOf(Integer.parseInt(labelHPCount.getText()) - 1));
                                labelSTCount.setText(String.valueOf(Integer.parseInt(labelSTCount.getText()) - 1));
                                try
                                {
                                    labelHandDamageThrCount.setText(DBConnect.getThrDamageOnST(Integer.parseInt(labelSTCount.getText())));
                                    labelHandDamageSwCount.setText(DBConnect.getSwDamageOnST(Integer.parseInt(labelSTCount.getText())));
                                } catch (SQLException e1)
                                {
                                    e1.printStackTrace();
                                }
                            }
                            break;
                        case "DX":
                            if (addOrDec)
                            {
                                labelBSCount.setText(String.valueOf(Float.parseFloat(labelBSCount.getText()) + 0.25));
                                labelDXCount.setText(String.valueOf(Integer.parseInt(labelDXCount.getText()) + 1));
                                labelBMCount.setText(String.valueOf((int)(Float.parseFloat(labelBSCount.getText()))));
                            }
                            else if (!labelDXCount.getText().equals("1"))
                            {
                                labelBSCount.setText(String.valueOf(Float.parseFloat(labelBSCount.getText()) - 0.25));
                                labelDXCount.setText(String.valueOf(Integer.parseInt(labelDXCount.getText()) - 1));
                                labelBMCount.setText(String.valueOf((int)(Float.parseFloat(labelBSCount.getText()))));
                            }
                            break;
                        case "IQ":
                            if (addOrDec)
                            {
                                labelIQCount.setText(String.valueOf(Integer.parseInt(labelIQCount.getText()) + 1));
                                labelWillCount.setText(String.valueOf(Integer.parseInt(labelWillCount.getText()) + 1));
                                labelPerCount.setText(String.valueOf(Integer.parseInt(labelPerCount.getText()) + 1));
                            }
                            else if (!labelIQCount.getText().equals("1"))
                            {
                                labelIQCount.setText(String.valueOf(Integer.parseInt(labelIQCount.getText()) - 1));
                                labelWillCount.setText(String.valueOf(Integer.parseInt(labelWillCount.getText()) - 1));
                                labelPerCount.setText(String.valueOf(Integer.parseInt(labelPerCount.getText()) - 1));
                            }                            break;
                        case "HT":
                            if (addOrDec)
                            {
                                labelBSCount.setText(String.valueOf(Float.parseFloat(labelBSCount.getText()) + 0.25));
                                labelHTCount.setText(String.valueOf(Integer.parseInt(labelHTCount.getText()) + 1));
                                labelFPCount.setText(String.valueOf(Integer.parseInt(labelFPCount.getText()) + 1));
                                labelBMCount.setText(String.valueOf((int)(Float.parseFloat(labelBSCount.getText()))));
                            }
                            else if (!labelHTCount.getText().equals("1"))
                            {
                                labelBSCount.setText(String.valueOf(Float.parseFloat(labelBSCount.getText()) - 0.25));
                                labelHTCount.setText(String.valueOf(Integer.parseInt(labelHTCount.getText()) - 1));
                                labelFPCount.setText(String.valueOf(Integer.parseInt(labelFPCount.getText()) - 1));
                                labelBMCount.setText(String.valueOf((int)(Float.parseFloat(labelBSCount.getText()))));
                            }                            break;
                        case "HP":
                            if (addOrDec)
                                labelHPCount.setText(String.valueOf(Integer.parseInt(labelHPCount.getText()) + 1));
                            else  if (!labelHPCount.getText().equals("1"))
                                labelHPCount.setText(String.valueOf(Integer.parseInt(labelHPCount.getText()) - 1));
                            break;
                        case "Will":
                            if (addOrDec)
                                labelWillCount.setText(String.valueOf(Integer.parseInt(labelWillCount.getText()) + 1));
                            else if (!labelWillCount.getText().equals("1"))
                                labelWillCount.setText(String.valueOf(Integer.parseInt(labelWillCount.getText()) - 1));
                            break;
                        case "Per":
                            if (addOrDec)
                                labelPerCount.setText(String.valueOf(Integer.parseInt(labelPerCount.getText()) + 1));
                            else if (!labelPerCount.getText().equals("1"))
                                labelPerCount.setText(String.valueOf(Integer.parseInt(labelPerCount.getText()) - 1));
                            break;
                        case "FP":
                            if (addOrDec)
                                labelFPCount.setText(String.valueOf(Integer.parseInt(labelFPCount.getText()) + 1));
                            else if (!labelFPCount.getText().equals("1"))
                                labelFPCount.setText(String.valueOf(Integer.parseInt(labelFPCount.getText()) - 1));
                            break;
                        case "BS":
                            if (addOrDec)
                            {
                                labelBMCount.setText(String.valueOf(Integer.parseInt(labelBMCount.getText())
                                        - (int)Float.parseFloat(labelBSCount.getText()) + (int)(Float.parseFloat(labelBSCount.getText()) + 0.25) ));
                                labelBSCount.setText(String.valueOf(Float.parseFloat(labelBSCount.getText()) + 0.25));
                            }
                            else if (!labelBSCount.getText().equals("1"))
                            {
                                labelBMCount.setText(String.valueOf(Integer.parseInt(labelBMCount.getText())
                                        - (int)Float.parseFloat(labelBSCount.getText()) + (int)(Float.parseFloat(labelBSCount.getText()) - 0.25) ));
                                labelBSCount.setText(String.valueOf(Float.parseFloat(labelBSCount.getText()) - 0.25));
                            }
                            break;
                        case "BM":
                            if (addOrDec)
                                labelBMCount.setText(String.valueOf(Integer.parseInt(labelBMCount.getText()) + 1));
                            else if (!labelBMCount.getText().equals("1"))
                                labelBMCount.setText(String.valueOf(Integer.parseInt(labelBMCount.getText()) - 1));
                            break;
                        default:
                            break;
                    }
                    Window.mathPoints();
                    Window.skillsPanel.remathSkillsCost();
                }
            });
        }
    }

    void clear()
    {
        labelSTCount.setText("10");
        labelDXCount.setText("10");
        labelIQCount.setText("10");
        labelHTCount.setText("10");
        labelHPCount.setText("10");
        labelWillCount.setText("10");
        labelPerCount.setText("10");
        labelFPCount.setText("10");
        labelBSCount.setText("5");
        labelBMCount.setText("5");
        labelBLCount.setText("20");
        labelHandDamageThrCount.setText("1d-2");
        labelHandDamageSwCount.setText("1d");
    }

    void saveStats()
    {
        int[] stats = new int[9];
        stats[0] = Integer.parseInt(labelSTCount.getText());
        stats[1] = Integer.parseInt(labelDXCount.getText());
        stats[2] = Integer.parseInt(labelIQCount.getText());
        stats[3] = Integer.parseInt(labelHTCount.getText());
        stats[4] = Integer.parseInt(labelHPCount.getText());
        stats[5] = Integer.parseInt(labelWillCount.getText());
        stats[6] = Integer.parseInt(labelPerCount.getText());
        stats[7] = Integer.parseInt(labelFPCount.getText());
        stats[8] = Integer.parseInt(labelBMCount.getText());
        try
        {
            DBConnect.saveStats(stats, Float.parseFloat(labelBSCount.getText()), Window.characterId);
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }

    String mathPoints()
    {
        String total = "|| Stats ||\n";
        int buf, points;
        buf = ((Integer.parseInt(labelSTCount.getText()) - 10) * 10);
        total += "|(ST): " + buf;
        points = buf;
        buf = ((Integer.parseInt(labelDXCount.getText()) - 10) * 20);
        total += " |(DX): " + buf ;
        points += buf;
        buf = ((Integer.parseInt(labelIQCount.getText()) - 10) * 20);
        total += " |(IQ): " + buf;
        points += buf;
        buf = ((Integer.parseInt(labelHTCount.getText()) - 10) * 10);
        total += " |(HT): " + buf;
        points += buf;
        buf = ((Integer.parseInt(labelHPCount.getText()) - Integer.parseInt(labelSTCount.getText())) * 2);
        total += " |(HP): " + buf + " |\n";
        points += buf;
        buf = ((Integer.parseInt(labelWillCount.getText()) - Integer.parseInt(labelIQCount.getText())) * 5);
        total += "|(Will): " + buf;
        points += buf;
        buf = ((Integer.parseInt(labelPerCount.getText()) - Integer.parseInt(labelIQCount.getText())) * 5);
        total += " |(Per): " + buf;
        points += buf;
        buf = ((Integer.parseInt(labelFPCount.getText()) - Integer.parseInt(labelHTCount.getText())) * 3);
        total += " |(FP): " + buf;
        points += buf;
        buf = (int)((Float.parseFloat(labelBSCount.getText()) * 4 - Integer.parseInt(labelHTCount.getText()) - Integer.parseInt(labelDXCount.getText())) * 5);
        total += " |(BS): " + buf;
        points += buf;
        buf = ((Integer.parseInt(labelBMCount.getText()) - (int)(Float.parseFloat(labelBSCount.getText()))) * 5);
        total += " |(BM):" + buf;
        points += buf;
        total += " |\n|| Total stats: " + points + " ||";
        Window.totalPoints[0] -= Window.totalPoints[1];
        Window.totalPoints[1] = points;
        Window.totalPoints[0] += points;
        if (!Window.infoPanel.getTextInitialPoints().getText().isEmpty())
            Window.infoPanel.getLabelRemainingPointsCount().setText(String.valueOf(Integer.parseInt(Window.infoPanel.getTextInitialPoints().getText()) - Window.totalPoints[0]));
        return total;
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);

        g.drawLine(getWidth()/2, labelSTCount.getY() - 5,
                getWidth()/2, getHeight() - 5);
        g.drawLine(5, labelSTCount.getY() - 5, getWidth()-5, labelSTCount.getY() - 5);
        g.drawLine(5, labelHTCount.getY() + labelHTCount.getHeight() + 5,
                getWidth()-5, labelHTCount.getY() + labelHTCount.getHeight() + 5);
        g.drawLine(getWidth()/2, labelBLCount.getY() + labelBLCount.getHeight() + 5,
                getWidth()-5, labelBLCount.getY() + labelBLCount.getHeight() + 5);


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

    public JLabel getLabelSTCount() {
        return labelSTCount;
    }

    public void setLabelSTCount(JLabel labelSTCount) {
        this.labelSTCount = labelSTCount;
    }

    public JLabel getLabelDXCount() {
        return labelDXCount;
    }

    public void setLabelDXCount(JLabel labelDXCount) {
        this.labelDXCount = labelDXCount;
    }

    public JLabel getLabelIQCount() {
        return labelIQCount;
    }

    public void setLabelIQCount(JLabel labelIQCount) {
        this.labelIQCount = labelIQCount;
    }

    public JLabel getLabelHTCount() {
        return labelHTCount;
    }

    public void setLabelHTCount(JLabel labelHTCount) {
        this.labelHTCount = labelHTCount;
    }

    public JLabel getLabelHPCount() {
        return labelHPCount;
    }

    public void setLabelHPCount(JLabel labelHPCount) {
        this.labelHPCount = labelHPCount;
    }

    public JLabel getLabelWillCount() {
        return labelWillCount;
    }

    public void setLabelWillCount(JLabel labelWillCount) {
        this.labelWillCount = labelWillCount;
    }

    public JLabel getLabelPerCount() {
        return labelPerCount;
    }

    public void setLabelPerCount(JLabel labelPerCount) {
        this.labelPerCount = labelPerCount;
    }

    public JLabel getLabelFPCount() {
        return labelFPCount;
    }

    public void setLabelFPCount(JLabel labelFPCount) {
        this.labelFPCount = labelFPCount;
    }

    public JLabel getLabelHandDamageSwCount() {
        return labelHandDamageSwCount;
    }

    public JLabel getLabelHandDamageThrCount() {
        return labelHandDamageThrCount;
    }

    public JLabel getLabelBMCount() {
        return labelBMCount;
    }

    public JLabel getLabelBLCount() {
        return labelBLCount;
    }

    public JLabel getLabelBSCount() {
        return labelBSCount;
    }
}
