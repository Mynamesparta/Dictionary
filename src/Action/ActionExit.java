package Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ActionExit implements ActionListener { 
	@Override
    public void actionPerformed(ActionEvent e) {
        
        // ��� ���������� (������� ��������)
       System.exit(1); // ���� �����������������, �� ����� ��������� ���������
     }
}
