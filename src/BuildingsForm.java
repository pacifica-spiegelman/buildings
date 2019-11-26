import buildings.Building;
import buildings.Buildings;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BuildingsForm extends JFrame {

    private JPanel panel;

    public BuildingsForm() {
        super("Start menu");
        components();
    }

    private void components(){
        JMenuBar jMenuBar = new JMenuBar();
        jMenuBar.add(createFileMenu());
        setJMenuBar(jMenuBar);
        setSize(300, 200);
        setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
        add(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JMenu createFileMenu() {
        JMenu jMenu = new JMenu("File");
        JMenuItem dwellingFile = new JMenuItem("Open dwelling..."); // maybe image
        JMenuItem officeFile = new JMenuItem("Open office building...");
        jMenu.add(dwellingFile);
        jMenu.add(officeFile);
        dwellingFile.addActionListener(actionEvent -> createDwelling());
        officeFile.addActionListener(actionEvent -> {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(this);
            File file = chooser.getSelectedFile();
            setVisible(true);
            try {
                Buildings.readBuilding(new FileReader(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return jMenu;
    }

    private void createDwelling() {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File file = chooser.getSelectedFile();
        setVisible(true);

        try {
            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            Building buildings = Buildings.readBuilding(new FileReader(file));
            JTextArea text = new JTextArea(buildings.toString());
            JScrollPane scrollPane = new JScrollPane(text);
            panel.add(scrollPane, BorderLayout.CENTER);
            panel.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

