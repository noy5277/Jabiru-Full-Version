package Jabiru.View;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Jabiru.Classes.Issue;
import Jabiru.Classes.Project;
import Jabiru.Classes.User;
import Jabiru.View.IssueWindow.DeleteKeyCommand;
import Jabiru.View.IssueWindow.SaveButtonCommand;
import Jabiru.client.Client;

public class MainWindow extends JFrame implements ActionListener, Observer
{
	private Client client;
	private JPanel contentPane;
	private Map<Integer ,Issue> issueMap;
	private Map<Integer ,Project> projectMap;
	private Map<Integer ,User> userMap;
	private ActionListener refreseBtnAction;
    private ActionListener saveBtnAction;
    private ActionListener addBtnAction;
    private ActionListener deleteBtnAction;
    private JTree tree;
    private JTable table;
    private Icommand saveButtonCommand;
    private Icommand deleteCommand;
    private Icommand addCommand;
    private Icommand refreshTableCommand;
    private JButton saveBtn;
    private JButton deleteBtn;
    private JButton addBtn;
    private JButton refreseBtn;
    
    private final String[] titleAllIssues = {"","ID","Project","Status","Priority","categoty","Date Submitted","Assigned To","Updated","Subject","Due Date"};
	private final String[] titleAssignedToMe= {"","ID","Subject","Status","Priority"};
	private final String[] titleAllUsers= {"","UserName","RealName","Password","Email","Enabled"};
	private final String[] titleAllProjects= {"","Name","Contact","Phone","Enabled","Email","SLA"};
	
	
	private Object allIssuedata[]=new Object[11];
	private Object AssignedToMedata[]=new Object[5];
	private Object AllUsersdata[]=new Object[6];
	private Object AllProjectsdata[]=new Object[7];
	
	
    private Object [][] AssignedToMeEmpty= {{"","","","",""}};
    private Object [][] allIssuedataEmpty= {{"","","","","","","","","","",""}};
    private Object [][] AllUsersEmpty= {{"","","","","",""}};
    private Object [][] AllProjectsEmpty= {{"","","","","","",""}};
    
    private JPopupMenu popupMenu;
    JMenuItem menuItemView = new JMenuItem("View");
    JMenuItem menuItemDelete = new JMenuItem("Delete");
	JMenuItem menuItemEdit = new JMenuItem("Edit");
	
	DefaultTableModel AssignedTable = new DefaultTableModel(AssignedToMeEmpty,titleAssignedToMe);
	DefaultTableModel IssuesTable = new DefaultTableModel(allIssuedataEmpty,titleAllIssues);
	DefaultTableModel AllUsersTable = new DefaultTableModel(AllUsersEmpty,titleAllUsers);
	DefaultTableModel AllProjectsTable = new DefaultTableModel(AllProjectsEmpty,titleAllProjects);
	
	private JMenuBar menuBar;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					//MainWindow frame =new  MainWindow();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public MainWindow(Client client,ArrayList<Issue>AssignedTable,ArrayList<Issue>IssuesTable,ArrayList<User>allUsersTable,ArrayList<Project>allProjectTable )
	{
		this.client=client;
		this.saveButtonCommand=new SaveButtonCommand();
		this.deleteCommand=new DeleteCommand();
		
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	//////////////////////////////////////////////////////////
	////  COMMANDS 
	///
	//////////////////////////////////////////////////
    
	class SaveButtonCommand implements Icommand
	{

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void unexecute() {
			// TODO Auto-generated method stub
			
		}
	
	}
	
	class DeleteCommand implements Icommand
	{
		public DeleteCommand()
		{
			
		}

		@Override
		public void execute() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void unexecute() {
			// TODO Auto-generated method stub
			
		}
		
	}
	

	
	
	
}
