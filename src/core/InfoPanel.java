package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class InfoPanel extends JPanel
{
    private final JTextField textInitialPoints;
    private final JTextField textName;
    private final JTextField textAge;
    private final JLabel labelRemainingPointsCount;
    private final JTextArea textDescription;
    private final JTextField textHeight;
    private final JTextField textWeight;
    private final JTextField textRace;

    InfoPanel ()
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
        c.insets = new Insets(10, 10, 0, 10);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        setBackground(Color.CYAN);
//===========================Labels===================================
//------------------labelName-----------------------
        JLabel labelName = new JLabel("Name");
        labelName.setFont(Resources.font15B);
        labelName.setToolTipText("Имя персонажа");
        c.gridx = 1;
        c.gridy = 1;
        gbl.setConstraints(labelName, c);
        add(labelName);
//------------------labelName-----------------------
//------------------labelRace------------------------
        JLabel labelRace = new JLabel("Race");
        labelRace.setFont(Resources.font15B);
        labelRace.setToolTipText("Расса персонажа");
        c.gridx = 1;
        c.gridy = 2;
        gbl.setConstraints(labelRace, c);
        add(labelRace);
//------------------labelAge------------------------
//------------------labelAge------------------------
        JLabel labelAge = new JLabel("Age");
        labelAge.setFont(Resources.font15B);
        labelAge.setToolTipText("Возраст персонажа");
        c.gridx = 1;
        c.gridy = 3;
        gbl.setConstraints(labelAge, c);
        add(labelAge);
//------------------labelAge------------------------
//------------------labelHeight------------------------
        JLabel labelHeight = new JLabel("Height");
        labelHeight.setFont(Resources.font15B);
        labelHeight.setToolTipText("Рост персонажа");
        c.gridx = 3;
        c.gridy = 3;
        gbl.setConstraints(labelHeight, c);
        add(labelHeight);
//------------------labelHeight------------------------
//------------------labelWeight------------------------
        JLabel labelWeight = new JLabel("Weight");
        labelWeight.setFont(Resources.font15B);
        labelWeight.setToolTipText("Вес персонажа");
        c.gridx = 5;
        c.gridy = 3;
        gbl.setConstraints(labelWeight, c);
        add(labelWeight);
//------------------labelWeight------------------------
//------------------labelInitialPoints-----------------------
        JLabel labelInitialPoints = new JLabel("Initial points");
        labelInitialPoints.setFont(Resources.font15B);
        labelInitialPoints.setToolTipText("Начальные очки персонажа");
        c.gridwidth  = 2;
        c.gridx = 1;
        c.gridy = 4;
        gbl.setConstraints(labelInitialPoints, c);
        add(labelInitialPoints);
//------------------labelInitialPoints-----------------------
//------------------labelRemainingPoints-----------------------
        JLabel labelRemainingPoints = new JLabel("Remaining points");
        labelRemainingPoints.setFont(Resources.font15B);
        labelRemainingPoints.setToolTipText("Очков персонажа осталось");
        c.gridx = 4;
        c.gridy = 4;
        gbl.setConstraints(labelRemainingPoints, c);
        add(labelRemainingPoints);
//------------------labelRemainingPoints-----------------------
//------------------labelRemainingPointsCount-----------------------
        labelRemainingPointsCount = new JLabel();
        labelRemainingPointsCount.setFont(Resources.font15);
        c.gridwidth  = 1;
        c.gridx = 6;
        c.gridy = 4;
        gbl.setConstraints(labelRemainingPointsCount, c);
        add(labelRemainingPointsCount);
//------------------labelRemainingPointsCount-----------------------
//------------------labelDescription-----------------------
        JLabel labelDescription = new JLabel("Description");
        labelDescription.setFont(Resources.font15B);
        labelDescription.setToolTipText("Описание персонажа");
        c.gridx = 1;
        c.gridy = 5;
        gbl.setConstraints(labelDescription, c);
        add(labelDescription);
//------------------labelRemainingPointsCount-----------------------
//===========================Labels===================================
//===========================TextFields===============================
        KeyListener keyListener = new java.awt.event.KeyAdapter()
    {
        @Override
        public void keyTyped(KeyEvent e)
        {
            Window.isChanged = true;
        }
    };
//------------------textName-----------------------
        textName = new JTextField();
        textName.setFont(Resources.font15);
        textName.addKeyListener(keyListener);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth  = GridBagConstraints.RELATIVE;
        c.gridx = 2;
        c.gridy = 1;
        c.weightx = 1;
        gbl.setConstraints(textName, c);
        add(textName);
//------------------textName-----------------------
//------------------textRace-----------------------
        textRace = new JTextField();
        textRace.setFont(Resources.font15);
        textRace.addKeyListener(keyListener);
        c.gridx = 2;
        c.gridy = 2;
        c.gridwidth = 3;
        gbl.setConstraints(textRace, c);
        add(textRace);
//------------------textRace-----------------------
//------------------textAge-----------------------
        textAge = new JTextField();
        textAge.setFont(Resources.font15);
        textAge.addKeyListener(keyListener);
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 1;
        gbl.setConstraints(textAge, c);
        add(textAge);
//------------------textAge-----------------------
//------------------textHeight-----------------------
        textHeight = new JTextField();
        textHeight.setFont(Resources.font15);
        textHeight.addKeyListener(keyListener);
        c.gridx = 4;
        c.gridy = 3;
        gbl.setConstraints(textHeight, c);
        add(textHeight);
//------------------textHeight-----------------------
//------------------textWeight-----------------------
        textWeight = new JTextField();
        textWeight.setFont(Resources.font15);
        textWeight.addKeyListener(keyListener);
        c.gridx = 6;
        c.gridy = 3;
        gbl.setConstraints(textWeight, c);
        add(textWeight);
//------------------textWeight-----------------------
//------------------textInitialPoints-----------------------
        textInitialPoints = new JTextField();
        textInitialPoints.setFont(Resources.font15);
        textInitialPoints.addKeyListener(keyListener);
        c.gridx = 3;
        c.gridy = 4;
        gbl.setConstraints(textInitialPoints, c);
        add(textInitialPoints);
//------------------textInitialPoints-----------------------
//------------------textDescription-----------------------
        textDescription = new JTextArea();
        textDescription.setFont(Resources.font15);
        textDescription.addKeyListener(keyListener);
        textDescription.setLineWrap(true);
        c.insets = new Insets(10, 10, 20, 10);
        c.gridx = 1;
        c.gridy = 6;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.gridheight = GridBagConstraints.RELATIVE;
        gbl.setConstraints(textDescription, c);
        add(textDescription);
//------------------textInitialPoints-----------------------

//===========================TextFields===============================
    }

    void clear()
    {
        textName.setText("name");
        textAge.setText("0");
        textInitialPoints.setText("100");
        labelRemainingPointsCount.setText("100");
        textDescription.setText("description");
        textHeight.setText("1");
        textWeight.setText("1");
        textRace.setText("race");
    }

    void saveStats()
    {
        String[] info = new String[8];
        info[0] = textName.getText();
        info[1] = textAge.getText();
        info[2] = textInitialPoints.getText();
        info[3] = labelRemainingPointsCount.getText();
        info[4] = textDescription.getText();
        info[5] = textHeight.getText();
        info[6] = textWeight.getText();
        info[7] = textRace.getText();

        try
        {
            DBConnect.saveInfo(info, Window.characterId);
        } catch (SQLException e1)
        {
            e1.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);


        g.drawLine(5, textName.getY() - 5, getWidth()-5, textName.getY() - 5);
        g.drawLine(5, textName.getY() + textName.getHeight() + 5,
                getWidth()-5, textName.getY() + textName.getHeight() + 5);
        g.drawLine(5, textRace.getY() + textRace.getHeight() + 5,
                getWidth()-5, textRace.getY() + textRace.getHeight() + 5);
        g.drawLine(5, textAge.getY() + textAge.getHeight() + 5,
                getWidth()-5, textAge.getY() + textAge.getHeight() + 5);
        g.drawLine(5, textInitialPoints.getY() + textInitialPoints.getHeight() + 5,
                getWidth()-5, textInitialPoints.getY() + textInitialPoints.getHeight() + 5);


    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Image im = null;
        try {
            im = ImageIO.read(new File("bg_blue.png"));
        } catch (IOException ignored) {}
        g.drawImage(im, 0, 0, getWidth(), getHeight(), null);
    }

    public JTextField getTextInitialPoints() {
        return textInitialPoints;
    }

    public JTextField getTextName() {
        return textName;
    }

    public JTextField getTextAge() {
        return textAge;
    }

    public JLabel getLabelRemainingPointsCount() {
        return labelRemainingPointsCount;
    }

    public JTextArea getTextDescription() {
        return textDescription;
    }

    public JTextField getTextHeight() {
        return textHeight;
    }

    public JTextField getTextWeight() {
        return textWeight;
    }

    public JTextField getTextRace() {
        return textRace;
    }
}
