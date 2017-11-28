import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JFServer extends JFrame{
    private int ScreenW = Toolkit.getDefaultToolkit().getScreenSize().width;
    private int ScreenH = Toolkit.getDefaultToolkit().getScreenSize().height;
    private int frmW = 600,frmH = 500;
    private JButton jbtnox[] = new JButton[9];
    private JTextArea jtaInput = new JTextArea();
    private JScrollPane jsc = new JScrollPane(jtaInput);
    private JTextArea jtaOutput = new JTextArea();
    private JButton jbtnSend = new JButton("Send");
    private JButton jbtnStart = new JButton("Start");
    private JButton jbtnStop = new JButton("Stop");
    private JButton jbtnExit=  new JButton("Exit");
    private JLabel jlabIP = new JLabel("Server IP:");
    private JLabel jIP = new JLabel("127.0.0.1");
    private JLabel jlabPort = new JLabel("Port :");
    private JTextField jtxPort = new JTextField("2222");
    private JPanel jpnSend = new JPanel(new BorderLayout(2,2));
    private JPanel jpnControl = new JPanel(new GridLayout(7,1,3,3));
    private JPanel jpnox = new JPanel(new GridLayout(3,3,3,3));
    private Container cp;
    public JFServer(){
        init();

    }
    private void init(){
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(ScreenW/2-frmW/2,ScreenH/2-frmH/2,frmW,frmW);
        this.setTitle("Server");
        cp = this.getContentPane();
        cp.setLayout(new BorderLayout(3,3));
        cp.add(jsc,BorderLayout.WEST);
//        jtaInput.setPreferredSize(new Dimension(200,100));
        jtaInput.setLineWrap(true);
        jtaInput.setEditable(false);
        cp.add(jpnControl,BorderLayout.EAST);
        cp.add(jpnox,BorderLayout.CENTER);
        cp.add(jpnSend,BorderLayout.SOUTH);
        for(int i = 0;i<9;i++){
            jbtnox[i] = new JButton();
            jpnox.add(jbtnox[i]);
        }
        jpnSend.add(jtaOutput,BorderLayout.CENTER);
        jpnSend.add(jbtnSend,BorderLayout.EAST);
        jbtnSend.setPreferredSize(new Dimension(100,30));
        jpnControl.add(jlabIP);
        jpnControl.add(jIP);
        jpnControl.add(jlabPort);
        jpnControl.add(jtxPort);
        jpnControl.add(jbtnStart);
        jpnControl.add(jbtnStop);
        jpnControl.add(jbtnExit);
        jbtnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
    }
    public void addMessage(String instr){
        jtaInput.append("Client"+instr+"\n");
    }
}
