import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class MainFrame extends JFrame{
    private LoginFram lgfm;
    private int screenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int screenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 500, frmH = 400;
    //清單建立元件
    private JMenuBar jmb = new JMenuBar();
    private JMenu jmF = new JMenu("File");
    private JMenu jmSet = new JMenu("Set");
    private JMenu jmGame = new JMenu("Game");
    private JMenu jmAbout = new JMenu("About");
    private JMenuItem jmiExit = new JMenuItem("Exit");
    private JMenuItem jmiLotto = new JMenuItem("Lotto");
    private JMenuItem jmiCategory = new JMenuItem("Category");
    private JDesktopPane jdp = new JDesktopPane();
    //LOTTO子視窗
    private JInternalFrame jif = new JInternalFrame();
    private JButton jbtnClose  = new JButton("Close");
    private JButton jbtnRemake = new JButton("Rerandom");
    private int data[] = new int[6];
    private JLabel jlab[] = new JLabel[6];
    private Random rnd = new Random(System.currentTimeMillis());
    private JPanel jpnNumber = new JPanel(new GridLayout(1,6,3,3));
    private JPanel jpnControl = new JPanel(new GridLayout(1,2,3,3));

    //Math Keyboard子視窗

    //更改視窗字型元件
    private JPanel jPanel1 = new JPanel(new GridLayout(2,3,5,5));
    private JMenuItem jmiSetFont = new JMenuItem("Set Font");
    private JLabel jlabFamily = new JLabel("Family");
    private JLabel jlabStyle = new JLabel("Style");
    private JLabel jlabSize = new JLabel("Size");
    private JTextField jtxFamily = new JTextField("Time New Romans");
    private JTextField jtxSize = new JTextField("24");
    private String options[] ={"PLAIN","BOLD","ITALIC","BOLD+ITALIC"};
    private JComboBox jcombo = new JComboBox(options);
    //開檔案
    private JInternalFrame jifData = new JInternalFrame();
    private Container jDatacp = new Container();
    private JMenuBar jmbData = new JMenuBar();
    private JMenuItem jmiLoad = new JMenuItem("Load");
    private JMenuItem jmiNew = new JMenuItem("New");
    private JMenuItem jmiClose = new JMenuItem("Close");
    private JFileChooser jFichooser = new JFileChooser();
    private JTextArea jtxaData = new JTextArea();
    private JScrollPane jscro = new JScrollPane();

    public MainFrame(LoginFram lg){
        lgfm = lg;
        init();
    }
    private void init(){
        this.setBounds(screenW/2-frmW/2,screenH/2-frmH/2,frmW,frmH);
        jif.setBounds(0,0,300,200);
        this.setJMenuBar(jmb);
        this.setContentPane(jdp);
        jmb.add(jmF);
        jmb.add(jmSet);
        jmb.add(jmGame);
        jmb.add(jmAbout);
        jmF.add(jmiExit);
        jmGame.add(jmiLotto);
        jif.setLayout(new BorderLayout(3,3));
        jif.add(jpnControl,BorderLayout.NORTH);
        jif.add(jpnNumber,BorderLayout.CENTER);
        Number();
        jpnControl.add(jbtnClose);
        jpnControl.add(jbtnRemake);

        jmiExit.setAccelerator(KeyStroke.getKeyStroke('C',Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        jmSet.add(jmiSetFont);
        jPanel1.add(jlabFamily);
        jPanel1.add(jlabStyle);
        jPanel1.add(jlabSize);
        jPanel1.add(jtxFamily);
        jPanel1.add(jcombo);
        jPanel1.add(jtxSize);

        for(int i = 0;i<6;i++){

            jlab[i] = new JLabel();
            jlab[i].setHorizontalAlignment(SwingConstants.CENTER);
            jlab[i].setOpaque(true);
            jlab[i].setBackground(new Color(40,143,255));
            jlab[i].setText(Integer.toString(data[i]));

            jpnNumber.add(jlab[i]);
        }

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                lgfm.setVisible(true);
                dispose();
            }
        });
        //control
        jmiExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jmiLotto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jdp.add(jif);
                jif.setVisible(true);
            }
        });

        jbtnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jif.setVisible(false);
            }
        });

        jbtnRemake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Number();
                for(int i = 0;i<6;i++){
                    jlab[i].setText(Integer.toString(data[i]));
                }
            }
        });

        jmiSetFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        jPanel1,"Font setting",
                        JOptionPane.OK_CANCEL_OPTION);
                int fontStyle = 0;
                switch (jcombo.getSelectedIndex()){
                    case 0:
                        fontStyle = Font.PLAIN;
                        break;
                    case 1:
                        fontStyle = Font.BOLD;
                        break;
                    case 2:
                        fontStyle = Font.ITALIC;
                        break;
                    case 3:
                        fontStyle = Font.BOLD + Font.ITALIC;
                        break;
                }
                if(result == JOptionPane.OK_OPTION){
                    System.out.println("Test Message1111");
                    UIManager.put("Menu.font",new Font(jtxFamily.getText(),
                            fontStyle,Integer.parseInt(jtxSize.getText())));
//
                }
            }
        });
    }
    private void Number(){
        for(int i = 0;i <6;i++){
            data[i] = rnd.nextInt(42)+1;
            for(int j = 0;j<i;j++){
                if(data[i]==data[j]){
                    i--;
                    break;
                }
            }
        }
    }
}
