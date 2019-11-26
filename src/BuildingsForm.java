import buildings.Building;
import buildings.Buildings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildingsForm extends JFrame {

    private JPanel panel;
    private JPanel infoPanel;
    private JTextPane infoBuildPanel;
    private JTextPane infoFloorPanel;
    private JTextPane infoSpacePanel;
    private JScrollPane building;
    private JPanel scrollPane;
    private final static String DWELLING = "Dwelling";
    private final static String OFFICE = "Office";


    public BuildingsForm() {
        super("Start menu");
        components();
    }

    private void components() {
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        jMenuBar.add(createLookandFeelMenu());
        setJMenuBar(jMenuBar);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        add(panel);
        panel.setBackground(new Color(128, 128, 128));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        infoPanel.setBackground(new Color(169, 169, 169));
        scrollPane.setLayout(new BoxLayout(scrollPane, BoxLayout.Y_AXIS));
    }

    private JMenu createFileMenu() {
        JMenu jMenu = new JMenu("File");
        JMenuItem dwellingFile = new JMenuItem("Open dwelling...");
        JMenuItem officeFile = new JMenuItem("Open office building...");
        jMenu.add(dwellingFile);
        jMenu.add(officeFile);
        dwellingFile.addActionListener(actionEvent -> createBuilding(DWELLING));
        officeFile.addActionListener(actionEvent -> createBuilding(OFFICE));
        return jMenu;
    }

    private JMenu createLookandFeelMenu(){
        JMenu jMenu = new JMenu("Look & Feel");
        JRadioButtonMenuItem windows = new JRadioButtonMenuItem("Windows");
        JRadioButtonMenuItem motif = new JRadioButtonMenuItem("Motif");
        JRadioButtonMenuItem java = new JRadioButtonMenuItem("Java(Metal)");
        jMenu.add(windows);
        jMenu.add(motif);
        jMenu.add(java);
        windows.addActionListener(actionEvent -> setLookandFeel(0));
        motif.addActionListener(actionEvent -> setLookandFeel(1));
        java.addActionListener(actionEvent -> setLookandFeel(2));
        return jMenu;
    }

    private void createBuilding(String constant) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("C:\\Users\\Nastya\\Desktop"));
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        setVisible(true);
        try {
            Building buildings = Buildings.readBuilding(new FileReader(file));
            if (constant.equals(DWELLING)) {
                infoFloorPanel.setText("");
                infoSpacePanel.setText("");
                scrollPane.removeAll();
                infoBuildPanel.setText(DWELLING + "\nFloors: " + buildings.getFloorAmount() + "\nAll area: " + buildings.getSpaceArea());
            } else {
                infoFloorPanel.setText("");
                infoSpacePanel.setText("");
                scrollPane.removeAll();
                infoBuildPanel.setText(OFFICE + "\nFloors: " + buildings.getFloorAmount() + "\nAll area: " + buildings.getSpaceArea());
            }
            for (int i = 0, count = 0; i < buildings.getFloorAmount(); i++) {
                JPanel floorPanel = new JPanel();
                floorPanel.setLayout(new BoxLayout(floorPanel, BoxLayout.X_AXIS));
                for (int j = 0; j < buildings.getFloor(i).getSpaceAmount(); j++, count++) {
                    JButton button = new JButton();
                    button.setText(String.valueOf(count));
                    int finalI = i;
                    int finalCount = count;
                    int finalJ = j;
                    button.addActionListener(actionEvent -> {
                        infoFloorPanel.setText("Number of floor: " + finalI + "\nFlats: " + buildings.getFloor(finalI).getSpaceAmount() + "\nAll area: " + buildings.getFloor(finalI).getSpaceArea());
                        infoSpacePanel.setText("Number: " + finalCount + "\nRooms: " + buildings.getFloor(finalI).getSpace(finalJ).getRoom() + "\nAll area: " + buildings.getFloor(finalI).getSpace(finalJ).getArea());
                    });
                    floorPanel.add(button);
                }
                scrollPane.add(floorPanel);
            }

        } catch (IOException e) {
            new JOptionPane().createDialog("Reading error").show();
        }
    }

    private void setLookandFeel(int value){
        String s;
        switch (value){
            case 0: s = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
                break;
            case 1: s = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
            case 2: s = "javax.swing.plaf.metal.MetalLookAndFeel";
                break;
            default: s = UIManager.getSystemLookAndFeelClassName();
        }
        try {
            UIManager.setLookAndFeel(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        SwingUtilities.updateComponentTreeUI(getContentPane());
    }

}

