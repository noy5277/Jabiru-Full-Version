package Jabiru.View;
import java.awt.Component;
import java.awt.EventQueue;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ProgressBarUI;

import java.awt.Toolkit;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.JTable;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import keyper.Bank;
import keyper.Key;
import keyper.MasterPassword;


import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.Font;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.sql.SQLException;

import javax.swing.JProgressBar;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JMenu;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;

@SuppressWarnings("serial")
public class IssueWindow extends JFrame implements ActionListener, Observer {

	private JPanel contentPane;
    private MasterPassword master;
    private Map<Integer ,Key> keyMap;
    private ActionListener connectBtnAction;
    private ActionListener refreseBtnAction;
    private ActionListener saveBtnAction;
    private ActionListener addkeyBtnAction;
    private ActionListener deleteKeyBtnAction;
    private ActionListener copyUsernameBtnAction;
    private ActionListener copyPasswordBtnAction;
    private ActionListener lockBtnAction;
    private ActionListener newDatabaseAction;
    private JTree tree;
    private Key k;
    private JTable table;
    private NewDatabaseFram newDbframe;
    private Icommand connectFramCommand;
    private Icommand saveButtonCommand;
    private Icommand newDatabaseCommand;
    private Icommand copyUsernameCommand;
    private Icommand copyPasswordCommand;
    private Icommand lockDbCommand;
    private Icommand refreshTableCommand;
    private Icommand deletekeyCommand;
    private Icommand addKeyCommand;
    private JButton saveBtn;
    private final String[] columnNames = {"","Title", "UserName","Password", "URL"};
    private Object data[]=new Object[5];
    private Object [][] empty= {{"","","","",""}};
    private JProgressBar progressBar;
    private JPopupMenu popupMenu;
    private JButton deleteKeyBtn;
    private JButton copyUsernameBtn;
    private JButton copyPasswordBtn;
    private JButton addkeyBtn;
    private JButton lockBtn;
    private JButton refreseBtn;
    JMenuItem menuItemView = new JMenuItem("View");
	JMenuItem menuItemDelete = new JMenuItem("Delete");
	JMenuItem menuItemEdit = new JMenuItem("Edit");
	JMenuItem menuItemCopyUsername = new JMenuItem("Copy Username");
	JMenuItem menuItemCopyPassword = new JMenuItem("Copy Password");
    
    DefaultTableModel emptyTable = new DefaultTableModel(empty,columnNames);
    private JTextField keytextField;
    private JMenuBar menuBar;
    private JMenuItem openDatabaseBtn;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					MasterPassword master=new MasterPassword();
					BankListWindow frame = new BankListWindow(master);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public BankListWindow(MasterPassword master) {
		
		
		this.addKeyCommand=new AddKeyCommand(master.getmBank());
		this.deletekeyCommand=new DeleteKeyCommand(master.getmBank());
		this.refreshTableCommand=new RefreshTableCommand();
		this.lockDbCommand=new LockDbCommand();
		this.copyPasswordCommand=new CopyPasswordCommand();
		this.copyUsernameCommand=new CopyUsernameCommand();
		this.newDatabaseCommand=new NewDatabaseCommand();
		this.saveButtonCommand=new SaveButtonCommand();
		this.connectFramCommand=new ConnectFramCommand();
		
		InitActionListeners();
		this.master=master;
		this.keyMap=new HashMap <Integer ,Key>();
		this.progressBar=new JProgressBar(0,master.getmConf().getmClipBoardSleepTime());
		this.popupMenu=new JPopupMenu();
		setTitle("Jubiru");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\noyz\\git\\Jabiru\\Jabiru\\Icons\\jabiru.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 786, 511);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tree = new JTree();
		tree.setBorder(new LineBorder(new Color(102, 102, 102)));
		DefaultMutableTreeNode games=new DefaultMutableTreeNode("Games");
		DefaultMutableTreeNode internet=new DefaultMutableTreeNode("Internet");
		DefaultMutableTreeNode email=new DefaultMutableTreeNode("Email");
		DefaultMutableTreeNode group=new DefaultMutableTreeNode("Groups");
		DefaultTreeModel grouptree=new DefaultTreeModel(group);
		tree.setShowsRootHandles(true);
		tree.setFont(new Font("Myanmar Text", Font.PLAIN, 12));
		ImageIcon leafIcon=new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/folder-key-icon.png"));
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
		if (leafIcon != null)
		{	    
		    renderer.setLeafIcon(leafIcon);
		    tree.setCellRenderer(renderer);
		}
		group.add(email);
		group.add(internet);
		group.add(games);
		tree.setModel(grouptree);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
		    public void valueChanged(TreeSelectionEvent e) {
		        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
		                           tree.getLastSelectedPathComponent();
		        if (node == null) return;
		        Object nodeInfo = node.getUserObject();
		        String treeselected=nodeInfo.toString();
                
		       	addColByGroup(treeselected);
		        
		    }
		});
		tree.setToolTipText("Keys Groups");
		tree.setBounds(10, 71, 167, 346);
		contentPane.add(tree);

		table = new JTable(){
			   @Override
			   public boolean isCellEditable(int row, int column) {
			    return false;
			   }
		};
		table.setShowVerticalLines(false);
		table.setSurrendersFocusOnKeystroke(true);
		table.setFillsViewportHeight(true);
		table.setBounds(1, 107, 571, 247);
		contentPane.add(table);
		
		 JScrollPane settingsmenu = new JScrollPane(table);
		 settingsmenu.setBounds(187, 71, 571, 346);
		 getContentPane().add(settingsmenu);
		
		 menuItemView.addActionListener(this);
		 menuItemDelete.addActionListener(this);
		 menuItemEdit.addActionListener(this);
		 menuItemCopyUsername.addActionListener(this);
		 menuItemCopyPassword.addActionListener(this);
		 
		 popupMenu.add(menuItemView);
		 popupMenu.add(menuItemDelete);
		 popupMenu.add(menuItemEdit);
		 popupMenu.add(menuItemCopyUsername);
		 popupMenu.add(menuItemCopyPassword);
		 
		 progressBar.setForeground(new Color(46, 139, 87));
		 progressBar.setBounds(596, 428, 152, 19);
		 contentPane.add(progressBar);
		 
		 keytextField = new JTextField();
		 keytextField.setEditable(false);
		 keytextField.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 10));
		 keytextField.setBackground(SystemColor.menu);
		 keytextField.setBounds(10, 428, 580, 33);
		 contentPane.add(keytextField);
		 keytextField.setColumns(10);
		 
		 table.addMouseListener(new TableMouseListener(table,keytextField,keyMap));
		 table.setComponentPopupMenu(popupMenu);
		 
		 menuBar = new JMenuBar();
		 menuBar.setBounds(0, 0, 770, 33);
		 contentPane.add(menuBar);
		 
		 JMenu filemenu = new JMenu("File");
		 menuBar.add(filemenu);
		 
		 JMenuItem newDatabaseBtn = new JMenuItem("New Database");
		 newDatabaseBtn.setIcon(null);
		 filemenu.add(newDatabaseBtn);
		 newDatabaseBtn.addActionListener(newDatabaseAction);
		 
		 openDatabaseBtn = new JMenuItem("Open");
		 filemenu.add(openDatabaseBtn);
		 
		 JMenuItem connectBtn = new JMenuItem("Connect ");
		 filemenu.add(connectBtn);
		 connectBtn.addActionListener(connectBtnAction);
		 
		 JMenu groupsmenu = new JMenu("Groups");
		 menuBar.add(groupsmenu);
		 
		 JMenu setingsmenu = new JMenu("Settings");
		 setingsmenu.setIcon(null);
		 menuBar.add(setingsmenu);
		 
		 JMenu actionMenu = new JMenu("Actions");
		 menuBar.add(actionMenu);
		 
		 JMenu aboutMenu = new JMenu("About");
		 menuBar.add(aboutMenu);
		 
		 refreseBtn = new JButton("");
		 refreseBtn.setToolTipText("Refresh");
		 refreseBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/refresh.png")));
		 refreseBtn.setBounds(158, 35, 27, 27);
		 contentPane.add(refreseBtn);
		 refreseBtn.addActionListener(refreseBtnAction);
		 
		 saveBtn = new JButton("");
		 saveBtn.setEnabled(false);
		 saveBtn.setToolTipText("Save");
		 saveBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/save.png")));
		 saveBtn.setBounds(187, 35, 27, 27);
		 contentPane.add(saveBtn);
		 saveBtn.addActionListener(saveBtnAction);
		 
		 
		 addkeyBtn = new JButton("");
		 addkeyBtn.setToolTipText("Add key");
		 addkeyBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/addkey.png")));
		 addkeyBtn.setSelectedIcon(null);
		 addkeyBtn.setBounds(129, 35, 27, 27);
		 contentPane.add(addkeyBtn);
		 addkeyBtn.addActionListener(addkeyBtnAction);
		 
		 deleteKeyBtn = new JButton("");
		 deleteKeyBtn.setToolTipText("Delete key");
		 deleteKeyBtn.setEnabled(false);
		 deleteKeyBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/deletekey.png")));
		 deleteKeyBtn.setBounds(99, 35, 27, 27);
		 contentPane.add(deleteKeyBtn);
		 deleteKeyBtn.addActionListener(deleteKeyBtnAction);
		 
		 copyUsernameBtn = new JButton("");
		 copyUsernameBtn.setToolTipText("Copy Username");
		 copyUsernameBtn.setEnabled(false);
		 copyUsernameBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/copyusername.png")));
		 copyUsernameBtn.setBounds(70, 35, 27, 27);
		 contentPane.add(copyUsernameBtn);
		 copyUsernameBtn.addActionListener(copyUsernameBtnAction);
		 
		 copyPasswordBtn = new JButton("");
		 copyPasswordBtn.setToolTipText("Copy Password");
		 copyPasswordBtn.setEnabled(false);
		 copyPasswordBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/copypassword.png")));
		 copyPasswordBtn.setBounds(40, 35, 27, 27);
		 contentPane.add(copyPasswordBtn);
		 copyPasswordBtn.addActionListener(copyPasswordBtnAction);
		 
		 lockBtn = new JButton("");
		 lockBtn.setToolTipText("Lock Database");
		 lockBtn.setIcon(new ImageIcon(BankListWindow.class.getResource("/keyper/View/Icons/Lock-Lock-icon-16.png")));
		 lockBtn.setBounds(10, 35, 27, 27);
		 contentPane.add(lockBtn);
		 lockBtn.addActionListener(lockBtnAction);
		 
	}
	
	private void EnableBtns()
	{
		copyPasswordBtn.setEnabled(true);
		copyUsernameBtn.setEnabled(true);
		deleteKeyBtn.setEnabled(true);
	}
	
	public void DisableAllButtons()
	{
		refreseBtn.setEnabled(false);
		saveBtn.setEnabled(false);
		addkeyBtn.setEnabled(false);
		lockBtn.setEnabled(false);
		addkeyBtn.setEnabled(false);
		copyPasswordBtn.setEnabled(false);
		copyUsernameBtn.setEnabled(false);
		deleteKeyBtn.setEnabled(false);
		
	}
	
	private void closewindow() 
	{
		this.dispose();
	}

	private void addColByGroup(String selected)
	{
	  Set<Key> keys=master.getmBank().getBank();
	  Set<Key>choosen=new HashSet<Key>();
     for(Key key: keys)
	 { 
        if(selected.equals(key.getmGroup()))
        {
        	choosen.add(key);
        }
        else
        {
        	System.out.println("there is no such group");
        }
     }
     populatetable(table,choosen);
	}
	
	public void selectAll()
	{
		Set<Key> keys=master.getmBank().getBank();
		 populatetable(table,keys);
	}
	
	public void removeRowSelection(JTable table)
	{
		table.setModel(emptyTable);
	}
	
	public  void populatetable(JTable table, Set<Key> keys)
	{
		int i=0;
		Renderer cell= new Renderer( );
	    removeRowSelection(table);
	    DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
	    TableColumnModel columnModel = table.getColumnModel();
	    columnModel.getColumn(0).setPreferredWidth(15);
	    tablemodel.setRowCount(0);
	     for(Key key: keys) 
	     {
	    	 keyMap.put(i, key);
	    	 System.out.println(key.getmExpired());
	    	 data[1]=key.getmTitle();
	    	 data[2]=key.getmUsername();
	    	 data[3]="**********";
	    	 data[4]=key.getmUrl();
	    	 System.out.println(key.getmTitle());
	    	 tablemodel.addRow(data);
	    	 if(key.ExpiredStatus())
	    	 {
	    		 cell.setKeyIcon(1);
	    	 } 
	    	 cell.getTableCellRendererComponent(table,data[0], true, true, i, 0); 
	    	 i++;
	    	 table.getColumnModel().getColumn(0).setCellRenderer(cell);
	    	 tablemodel=(DefaultTableModel) table.getModel();
	     }
	     table.setModel(tablemodel);
	     table.setRowHeight(20);
}


	class Renderer extends DefaultTableCellRenderer
    {  
	
      private int keyIcon;
      Renderer()
      {
     	this.keyIcon=0;
      }
	  JLabel lbl=new JLabel();
	  ImageIcon[] icons= {new ImageIcon(Login.class.getResource("/keyper/View/Icons/key-go-icon.jpg"))  , new ImageIcon(Login.class.getResource("/keyper/View/Icons/key-delete-icon.jpg"))};
	  @Override
	  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row ,int column)
	  {
		  lbl.setText((String)value);
		  lbl.setIcon(icons[keyIcon]);
		  return lbl;
	  }
	  public int getKeyIcon()
	  {
		return keyIcon;
	  }
	  public void setKeyIcon(int keyIcon)
	  {
		 this.keyIcon = keyIcon;
	  }
	  
   }
	
	 @Override
	 public void actionPerformed(ActionEvent event)
	 {
	    JMenuItem menu = (JMenuItem) event.getSource();
	    int selectedRow = table.getSelectedRow();
	    EnableBtns();
	    if (menu == menuItemView)
	    {
	    	HistoryKeyView view=new HistoryKeyView(keyMap.get(selectedRow),keyMap.get(selectedRow).getmExpired());
	    	view.setVisible(true);

	    }
	    else if(menu == menuItemDelete)
	    {
	    	deletekeyCommand.execute();
	    }
	    else if(menu == menuItemEdit)
	    {
	    	keyMap.get(selectedRow).addObserver(this);
	    	KeyViewWindow keyview=new KeyViewWindow(keyMap.get(selectedRow));
	    	keyview.setVisible(true);
	    }
	    else if(menu == menuItemCopyUsername)
	    {
			copyUsernameCommand.execute();
	    }
	    else if(menu == menuItemCopyPassword)
	    {
	    	copyPasswordCommand.execute();
	    }
	 }
	 
	 private void copyPassword(int selectedRow)
	 {
		 try {
			keyMap.get(selectedRow).copypassword();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 powerOnProgessbar();
	 }
	 
	  private void copyUsername(int selectedRow)
	 {	
		 try {
			keyMap.get(selectedRow).copyusername();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 powerOnProgessbar();		
	 }
	  
	 private void powerOnProgessbar()
	 {
		 Thread thread=new ProgressBarThread(progressBar,master.getmConf().getmClipBoardSleepTime());
		 thread.start();
	 }
	 
	private void InitActionListeners()
	{
		connectBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				connectFramCommand.execute();
			}
		};
		
		
		deleteKeyBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				deletekeyCommand.execute();
			}
			
		};
		
		refreseBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				refreshTableCommand.execute();
			}
		};
		
		saveBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				saveButtonCommand.execute();
			}
		};
		
		newDatabaseAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				newDatabaseCommand.execute();
			}
		};
		
		addkeyBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				addKeyCommand.execute();		
			}
		};
		
		copyUsernameBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				copyUsernameCommand.execute();
			}
		};
		
		copyPasswordBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				copyPasswordCommand.execute();
			}
		};
		
		lockBtnAction=new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				lockDbCommand.execute();
			}
		};	
	}
	
	
	@Override
	public void update(Observable o,Object arg)
	{
		System.out.println("notifyy successs");
		refreshTableCommand.execute();
		saveBtn.setEnabled(true);
		table.setModel(emptyTable);
		
	}
	
	////////////////////////////////////////////////////
	///Commands Patterns
	///
	///
	///////////////////////////////////////////////////
	
	class ConnectFramCommand implements Icommand
	{
		private Login fram;
		public ConnectFramCommand()
		{
			fram=new Login(newDbframe.getMaster());
		}
		
		public void execute()
		{ 
			fram.setVisible(true);
			closewindow();
		}
		
		public void unexecute()
		{
			
		}
	}
	
	class SaveButtonCommand implements Icommand
	{
		public SaveButtonCommand()
		{
			
		}
		
		public void execute()
		{
			try {
				master.getmDatabase().close(master.getmBank());
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			saveBtn.setEnabled(false);
		}
		
		public void unexecute()
		{
			
		}
	}
	
	class NewDatabaseCommand implements Icommand
	{
		public NewDatabaseCommand()
		{
			try {
				newDbframe = new NewDatabaseFram();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void execute()
		{
			newDbframe.setVisible(true);
		}
		
		public void unexecute() {
			
		}
		
	}
	
	
	
	class CopyUsernameCommand implements Icommand
	{
		public CopyUsernameCommand() {
			
		}
		
		public void execute()
		{
			int selectedRow = table.getSelectedRow();
			copyUsername(selectedRow);
		}
		
		public void unexecute()
		{
			
		}
	}
	
	class CopyPasswordCommand implements Icommand
	{
		public CopyPasswordCommand()
		{
			
		}
		
		public void execute()
		{
			int selectedRow = table.getSelectedRow();
			copyPassword(selectedRow);	
		}
		
		public void unexecute()
		{
			
		}
	}
	
	class LockDbCommand implements Icommand
	{
		public LockDbCommand()
		{
			
		}
		
		public void execute()
		{
			master.getmBank().getBank().clear();
			DisableAllButtons();
			master.getmDatabase().ShutDown();
		}
		
		public void unexecute()
		{
			
		}
	}
	
	
	
	class RefreshTableCommand implements Icommand
	{
		public RefreshTableCommand()
		{
			
		}
		
		public void execute()
		{
			DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
			 if (node == null) return;
			 Object nodeInfo = node.getUserObject();
			 String treeselected=nodeInfo.toString();  
			 addColByGroup(treeselected);
		}
		
		public void unexecute()
		{
			
		}
	}
	
	
	
	class DeleteKeyCommand implements Icommand
	{
		Bank bank;
		public DeleteKeyCommand(Bank b)
		{
			this.bank=b;
		}
		
		public void execute()
		{
			int n = JOptionPane.showConfirmDialog(
	    		    null,
	    		    "Are you sure you want to delete the key?",
	    		    "Warning",
	    		    JOptionPane.OK_CANCEL_OPTION);
	    	if(n==0)
	    	{
	    		int selectedRow = table.getSelectedRow();
	    		master.getmBank().removekey(keyMap.get(selectedRow));
	    		keyMap.get(selectedRow).notifyChanges();
	    	}
		}
		
		public void unexecute()
		{
			
		}
	}
	
	
	class AddKeyCommand implements Icommand,Observer
	{
		Bank bank;
		public AddKeyCommand(Bank b)
		{
			this.bank=b;
		}
	
		public void execute()
		{
			Key k=new Key("","","","","");
			k.addObserver(this);
			AddKeyFram addfram=new AddKeyFram(master, k);
			addfram.setVisible(true);
		}
		public void unexecute()
		{
		
		}
		@Override
		public void update(Observable o,Object arg)
		{
			System.out.println("notifyy successs");
			refreshTableCommand.execute();
			saveBtn.setEnabled(true);
			table.setModel(emptyTable);
			
		}	
	}
	
	
	
}

