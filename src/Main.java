import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.text.DecimalFormat;
import java.util.Vector;

import Action.*;
import SwicthLookAndFeelExampleApplication.LafItem;

public class Main extends JFrame {

	private JPanel panel=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel_for_QA=new JPanel();
	private JPanel panel_bStart=new JPanel();
	private JPanel panel_bNext=new JPanel();
	private JPanel panel_bToMenu=new JPanel();
	private JPanel panel_tf=new JPanel();
	private JTextArea text=new JTextArea(4,30);
	private Button bStart=new Button("Start");
	private Button bNext=new Button("Next");
	private Button bToMenu=new Button("to Menu");
	private ButtonGroup bg=new ButtonGroup();
	private JPanel panel_button_group=new JPanel();
	private JRadioButton rbRepeatFalse=new JRadioButton("без Повторень", true);
	private JRadioButton rbRepeatTrue=new JRadioButton("з Повтореннями", false);
	private JPanel panel_infinity=new JPanel();
	private JCheckBox cbInfinityTime=new JCheckBox("Нескінченно довго",true);
	private JFormattedTextField ftf_number_of_Repeat=new JFormattedTextField(new DecimalFormat("#####0"));
	private JTextField tf=new JTextField(30);
	private FileSystem fs;
	private Pairs_of_words pw;
    private JComboBox jComboBox = null;
	
	boolean isRepeat=false;
	boolean isInfinity=true;
	int number_of_iter=0;
	int w=0;
	int all;
	int iter;
	protected class LafItem {
        private UIManager.LookAndFeelInfo info;
        
        public LafItem(UIManager.LookAndFeelInfo info){
            this.info = info;
        }
        
        @Override public String toString(){
            return info.getName();
        }
        
        public UIManager.LookAndFeelInfo getLafInfo(){
            return this.info;
        }
        
    }
	public static void main(String[] args) {
		//SwicthLookAndFeelExampleApplication test=new SwicthLookAndFeelExampleApplication();
		//test.setVisible(true);
		//return;
		Main d=new Main();
		d.setVisible(true);
		
		//ActionListener  ex=new ActionExit();
		
		
		
	}
	
	public Main() {
		super("Dictionary");
		
		setSize(400,400);
		setResizable(false);
		//setLayout(new GridLayout(2,4)); 
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Font font=new Font("Serif",Font.PLAIN,14);
		
		//bStart.setSize(400, 100);
		getJComboBox();
		
		panel_bStart.add(bStart,BorderLayout.NORTH);
		panel_bNext.add(bNext, BorderLayout.NORTH);
		panel_bToMenu.add(bToMenu, BorderLayout.NORTH);
		panel_tf.add(tf);
		
		
		panel2.add(panel_bToMenu);
		panel2.add(panel_bNext);
		panel2.setLayout(new GridLayout(1,0));
		
		bg.add(rbRepeatFalse);
		bg.add(rbRepeatTrue);
		panel_button_group.add(rbRepeatFalse);
		panel_button_group.add(rbRepeatTrue);
		panel_button_group.add(cbInfinityTime);
		panel_button_group.add(ftf_number_of_Repeat);
		//panel_button_group.add(panel_infinity);
		panel_button_group.setLayout(new GridLayout(0,2));
		/*/
		panel_infinity.add(cbInfinityTime);
		panel_infinity.add(ftf_number_of_Repeat);
		panel_infinity.setVisible(true);
		panel_infinity.setLayout(new GridLayout(1,2));
		/*/
		ftf_number_of_Repeat.setEditable(true);
		ftf_number_of_Repeat.setColumns(10);
		ftf_number_of_Repeat.setValue(new Float(10.0f));
		
		
		panel.setLayout(new GridLayout(0,1));
		
		
		
		text.setFont(new Font("Serif",Font.PLAIN,24));
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		text.setEditable(false);
		bStart.setFont(font);
		bNext.setFont(font);
		bToMenu.setFont(font);
		
		bStart.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						text.setText("Hello Hello World!!");
					}
				});
		
		rbRepeatFalse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				isRepeat=false;
				//panel_infinity.setVisible(false);
				cbInfinityTime.setVisible(false);
				ftf_number_of_Repeat.setVisible(false);
			}
			
		});
		rbRepeatTrue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				isRepeat=true;
				//panel_infinity.setVisible(true);
				cbInfinityTime.setVisible(true);
				ftf_number_of_Repeat.setVisible(true);
				ftf_number_of_Repeat.setVisible(!isInfinity);
			}
			
		});

		cbInfinityTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				isInfinity=cbInfinityTime.isSelected();
				System.out.println(isInfinity);
				ftf_number_of_Repeat.setVisible(!isInfinity);
			}
			
		});
		ftf_number_of_Repeat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				ftf_number_of_Repeat.setValue(ftf_number_of_Repeat.getValue());
			}
			
		});
		
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Show(1);
				StartDictation();
			}
			
		});
		bToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Show(0);
			}
			
		});
		bNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				Next();
			}
			
		});
	
		add(panel);

		fs=new FileSystem("dictionary.txt");
		pw=new Pairs_of_words(fs.readLines());
		pw.setMode(false);
		
		Show(0);
	}
	
	void Show(int i)
	{
		panel.removeAll();
		//panel.add(jComboBox);
		switch(i)
		{
		case 0:
			//panel.add(text);
			//panel.add(tf);
			panel.add(panel_button_group);
			//panel.add(panel2);
			panel.add(panel_bStart);

			setSize(400,400);
			
			text.setVisible(false);
			tf.setVisible(false);
			panel_button_group.setVisible(true);
			//panel_infinity.setVisible(false); cbInfinityTime,ftf_number_of_Repeat
			cbInfinityTime.setVisible(false);
			ftf_number_of_Repeat.setVisible(false);
			isRepeat=false;
			rbRepeatFalse.setSelected(true);
			rbRepeatTrue.setSelected(false);
			bStart.setVisible(true);
			panel2.setVisible(false);
			break;
		case 1:
			panel.add(text);
			panel.add(panel_tf);
			//panel.add(panel_button_group);
			panel.add(panel2);
			//panel.add(bStart);

			setSize(1000,400);
			
			text.setVisible(true);
			tf.setVisible(true);
			panel_button_group.setVisible(false);
			bStart.setVisible(false);
			panel2.setVisible(true);
			break;
		default:
			break;
		}
	}
	
	Pairs_of_words.Question current_question;
	Pairs_of_words.Question last_question;
	void StartDictation()
	{
		all=0; w=0;
		iter=0;
		pw.setMode(isRepeat);
		bNext.setEnabled(true);
		current_question=pw.Take_Random();
		if(isRepeat&&!isInfinity)
		{
			System.out.println("Hello bag");
			number_of_iter=Integer.parseInt( ftf_number_of_Repeat.getText());
			if(number_of_iter==0)
			{
				bNext.setEnabled(false);
				return;
			}
			iter=0;
		}
		text.setText("Translate:"+current_question.question);
		
	}
	void Next()
	{
		all++;
		System.out.println(tf.getText().toLowerCase()+"=="+current_question.answer);
		boolean isCor=tf.getText().toLowerCase().equals(current_question.answer);
		last_question=current_question;
		current_question=pw.Take_Random();
		if(current_question.question!=null&&(!isRepeat||isInfinity||(iter+1)!=number_of_iter))
		{
			if(isCor)
			{
				w++;
				text.setText(w+"\\"+all+"("+(((double)w/all*100))+"%)\n"+
						     "Translate:"+current_question.question);
			}
			else
			{
				text.setText(w+"\\"+all+"("+(((double)w/all*100))+"%)\n"+
							"Corect answer:"+last_question.answer+" != "+tf.getText().toLowerCase()+"\n"+
							"Translate:"+current_question.question);
				
			}
		}
		else
		{
			if(isCor)
			{
				w++;
				text.setText(w+"\\"+all+"("+(((double)w/all*100))+"%");
			}
			else
			{
				text.setText(w+"\\"+all+"("+(((double)w/all*100))+"%)\n"+
							"Corect answer:"+last_question.answer+" != "+tf.getText().toLowerCase());
				
			}
			bNext.setEnabled(false);
			return;
		}
		if(!isRepeat)
		{
			
		}else if(isInfinity)
		{
			
		}
		else
		{
			iter++;
			if(iter==number_of_iter)
			{
				bNext.setEnabled(false);
			}
		}
	}
	
	private JComboBox getJComboBox() {
        if (jComboBox == null) {
            jComboBox = new JComboBox();
            jComboBox.setBounds(new Rectangle(20, 21, 256, 25));
            jComboBox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable(){
                        public void run(){
                            try {
                                UIManager.setLookAndFeel(((LafItem)jComboBox.getSelectedItem()).getLafInfo().getClassName());
                                SwingUtilities.updateComponentTreeUI(Main.this);
                            } catch ( Exception ex ) {
                                JOptionPane.showMessageDialog(Main.this, ex.toString());
                            }
                        }
                    });  
                }
            });
            
            // Searching for installed Look&Feel
            UIManager.LookAndFeelInfo[] laf = UIManager.getInstalledLookAndFeels();
            for (int i = 1, n = laf.length; i < 2; i++)
              jComboBox.addItem(new LafItem(laf[i]));
        }
        return jComboBox;
    }

}
