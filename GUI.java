import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

class GUI {
    private static JFrame frame;
    private static JLabel lb1, lb2;
    private static JLabel lb_isJsonSaved, lb_isDBSaved, lb_dataAmount;
    private static JLabel lb_indexQuery1, lb_indexQuery2;
    private static JLabel lb_status1, lb_status2, lb_status3;
    private static JLabel lb_author;
    private static JTextField tf1, tf_index;
    private static JButton btn1, btn2, btn3;
    private static JButton btn_indexSubmit, btn_visitLink, btn_visitShopLink;
    @SuppressWarnings("rawtypes")
	private static JComboBox cboBox;
    //////////////////////////////////
    private static boolean isJsonSaved;
    private static boolean isDBSaved;
    private static HipsterShopList hipsterShopList;
	private static String url;
	private static ArrayList<String> indices = new ArrayList<String>();
    
    public GUI() {
    	// 設定旗標為"尚未保存"，並獲取URL
    	isJsonSaved = false;
    	isDBSaved = false;
    	url = new GuiUtil().getOpenDataLink();
    	// 設定下拉選單初始值
    	indices.add("1");
    	// 定義(建立)各GUI元件
    	createWidgets();
    	// 設定觸發事件
    	setListeners();
        // 設定各元件位置、大小
        setBounds();
        // 設定文字屬性
        setFonts();
        // 設定元件初始禁能狀態
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        disableIndexInput();
        // Close JFrame       
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowHandler(frame)); 
    }
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void createWidgets() {
    	// 設定 JFrame
        frame = new JFrame("文創獵人 - 下載、剖析「文創商店」公開資料 (JSON格式) 並存入Mongo DB");
        frame.setLayout(null);
        frame.setSize(700, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // 標籤
        lb1 = new JLabel("公開資料URL: ");
        frame.add(lb1);
        lb2 = new JLabel("~(OWO)~");
        frame.add(lb2);
        lb_isJsonSaved = new JLabel("是否已爬取公開JSON資料: ");
        frame.add(lb_isJsonSaved);
        lb_isDBSaved = new JLabel("是否已儲存至芒果資料庫: ");
        frame.add(lb_isDBSaved);
        lb_status1 = new JLabel("否"); // 是/否 已爬取JSON資料
        frame.add(lb_status1);
        lb_status2 = new JLabel("否"); // 是/否 已存入芒果DB
        frame.add(lb_status2);
        lb_author = new JLabel("Author: Dada Liu");
        frame.add(lb_author);
        lb_dataAmount = new JLabel("資料筆數: ");
        frame.add(lb_dataAmount);
        lb_status3 = new JLabel("0"); // 是/否 已存入芒果DB
        frame.add(lb_status3);
        lb_indexQuery1 = new JLabel("我想看第");
        frame.add(lb_indexQuery1);
        lb_indexQuery2 = new JLabel("筆 JSON資料");
        frame.add(lb_indexQuery2);
        
        // 文字輸入區
        tf1 = new JTextField(url);
        frame.add(tf1);
        tf_index = new JTextField("");
        frame.add(tf_index);
        
        // 下拉選單
        cboBox = new JComboBox(indices.toArray());
        frame.add(cboBox);
        
        // 按鈕 
        btn1 = new JButton("JSON開爬！");
        frame.add(btn1);
        btn2 = new JButton("查看JSON資訊");
        frame.add(btn2);
        btn3 = new JButton("存入Mongo DB");
        frame.add(btn3);
        btn_indexSubmit = new JButton("確定");
        frame.add(btn_indexSubmit);
        btn_visitShopLink = new JButton("逛逛該商店");
        frame.add(btn_visitShopLink);
        btn_visitLink = new JButton("造訪公開JSON網頁");
        frame.add(btn_visitLink);
    }
    private static void setListeners() {
    	// 當 "爬JSON"按鈕 被按下所作之處理
        btn1.addActionListener( new ActionListener() {
            @SuppressWarnings("unchecked")
			@Override
            public void actionPerformed(ActionEvent e) {
            	String url_input = tf1.getText().toString();
                if(url_input.equals(url)) {
                	if(isJsonSaved) {
                		// 彈出警告視窗
                    	String optionTitle = "注意~";
                    	String optionContent = "偵測到您稍早已成功爬取JSON檔";
                    	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.ERROR_MESSAGE);
                    	
                    	/*String message = "<html><body><div width='200px' align='left'>This is some text!</div></body></html>";
                    	JLabel messageLabel = new JLabel(message);
                    	JOptionPane.showConfirmDialog(null, messageLabel);*/
		                	
                	}
                	else {
                		isJsonSaved = true;
                		lb_status1.setText("是");
                		lb_status1.setForeground(Color.BLUE);
                		// #開爬JSON
                    	try {
    						hipsterShopList = new GuiUtil().read_JSON(url_input);
    						lb_status3.setText(Integer.toString(hipsterShopList.getListCounter()));
    						cboBox.removeAllItems();
    						indices.clear();
    						for(int i=0; i<hipsterShopList.getListCounter(); i++) {
    							indices.add(Integer.toString(i+1));
    						}
    						for(String index: indices) {
    							cboBox.addItem(index);
    						}
    						btn2.setEnabled(true); 
    						btn3.setEnabled(true);
                    	} catch (IOException e1) {
    						e1.printStackTrace();
    					} catch (URISyntaxException e2) {
    						e2.printStackTrace();
    					}
                	}
                }
                else {
                	// 彈出警告視窗
                	String optionTitle = "深表遺憾~";
                	String optionContent = "本程式暫不支援其它(甚至\"任意\") JSON 內容爬取，請待未來開發\n";
                	optionContent += "目前只能使用預設輸入喔!\n";
                	optionContent += "預設輸入為「文化部文創商店列表JSON內容」\n";
                	optionContent += "（按下 OK ，還原文創商店URL）";
                	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.PLAIN_MESSAGE);
                	
                	// 還原輸入內容
                	tf1.setText(url);
                }
            }
        });
        
        // 當 "查看JSON資訊"按鈕 被按下所作之處理
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	enableIndexInput(); // 啟用"查看資料編號輸入區"
            	String optionTitle = "小提示";
            	String optionContent = "「我想看第 X 筆 JSON資料」輸入區已啟用\n";
            	optionContent += String.format("請在選擇您想看的資料編號，並按下確定\n(範圍: 1 ~ %d)", hipsterShopList.getListCounter());
            	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        // 當 "存入MongoDB"按鈕 被按下所作之處理
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(isJsonSaved) {
            		if(isDBSaved) {
            			// 彈出警告視窗
                    	String optionTitle = "注意~";
                    	String optionContent = "偵測到您稍早已成功儲存資料進芒果DB";
                    	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.ERROR_MESSAGE);
            		}
            		else {
            			// #開存
            			// 彈出警告視窗
                    	String optionTitle = "小提示";
                    	String optionContent = "正在儲存，請稍後~";
                    	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.INFORMATION_MESSAGE);
            			
                    	new GuiUtil().insertToMongoDB(hipsterShopList); // 儲存
                    	
                    	try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
                    	
                    	optionTitle = "小提示";
                    	optionContent = "儲存成功，請至 Mongo DB 查看~";
                    	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.INFORMATION_MESSAGE);
                    	
                    	isDBSaved = true;
            			lb_status2.setText("是");
            			lb_status2.setForeground(Color.BLUE);
                    	lb2.setText("\\\\\\ (^w^) /// ");
            		}
            	}
            	else {
            		// 彈出警告視窗
                	String optionTitle = "注意~";
                	String optionContent = "偵測到您尚未爬取JSON檔案，無法儲存";
                	JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.WARNING_MESSAGE);
            	}
            }
        });
        
        // 當使用者選取好要看的資料編號，並 "按下確認按鈕" 時
    	btn_indexSubmit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int selectedIndex = cboBox.getSelectedIndex();
            	String data = hipsterShopList.getHipsterShopInfo(selectedIndex); 
            	//System.out.println(data);
            	String optionTitle = String.format("公開 JSON 資料 - 第 %d 筆", (1+selectedIndex));
            	//String optionContent = data;
            	//JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.INFORMATION_MESSAGE);
            	
            	JTextArea textArea = new JTextArea(data);
            	JScrollPane scrollPane = new JScrollPane(textArea);  
            	textArea.setLineWrap(true);  
            	textArea.setWrapStyleWord(true); 
            	scrollPane.setPreferredSize( new Dimension(500, 500) );
            	JOptionPane.showMessageDialog(null, scrollPane, optionTitle,  
            	                                       JOptionPane.INFORMATION_MESSAGE);
            }
        });
    	
    	// 當使用者按下按鈕:"逛逛該商店" 時
    	btn_visitShopLink.addActionListener( new ActionListener() {
    		@Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		int selectedIndex = cboBox.getSelectedIndex();
                	String shopLink = hipsterShopList.getHipsterShopList().get(selectedIndex).getWebsite();
                	
                	if(shopLink.equals("")) {
                		String optionContent = "該文創商店沒有設計網站";
                		String optionTitle = "很抱歉";
                		JOptionPane.showMessageDialog(null, optionContent, optionTitle, JOptionPane.ERROR_MESSAGE);
                	} else {
                		visitLink(shopLink);
                	}
				} catch (URISyntaxException | IOException e1) {
					e1.printStackTrace();
				}
            }
        });
    	
    	// 當使用者按下按鈕:"造訪公開JSON網頁" 時
    	btn_visitLink.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	try {
            		visitLink(url);
				} catch (URISyntaxException | IOException e1) {
					e1.printStackTrace();
				}
            }
        });
    }
    private static void setFonts() {
    	tf1.setFont(new Font("Arial", Font.PLAIN, 15)); /// Font.SERIF
    	cboBox.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	cboBox.setForeground(Color.RED);
    	//tf_index.setFont(new Font(Font.SERIF, Font.PLAIN, 25));
    	//tf_index.setForeground(Color.RED);
    	lb1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
    	tf1.setFont(new Font("Arial", Font.PLAIN, 20));
    	btn1.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	btn2.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	btn3.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	btn_visitLink.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	btn_indexSubmit.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	btn_visitShopLink.setFont(new Font(Font.SERIF, Font.PLAIN, 15));
    	lb2.setFont(new Font("Arial", Font.PLAIN, 20));
    	lb_isJsonSaved.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
    	lb_isDBSaved.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
    	lb_dataAmount.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
    	lb_indexQuery1.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
    	lb_indexQuery1.setForeground(Color.RED);
    	lb_indexQuery2.setFont(new Font(Font.SERIF, Font.PLAIN, 18));
    	lb_indexQuery2.setForeground(Color.RED);
    	lb_status1.setFont(new Font(Font.SERIF, Font.BOLD, 18));
    	lb_status1.setForeground(Color.RED);
    	lb_status2.setFont(new Font(Font.SERIF, Font.BOLD, 18));
    	lb_status2.setForeground(Color.RED);
    	lb_status3.setFont(new Font(Font.SERIF, Font.BOLD, 25));
    	lb_author.setFont(new Font("Arial", Font.PLAIN, 10));
    }
    private static void setBounds() {
    	int X_LEFT = 25;
    	int X_ROW1 = 160;
    	int Y_TOP = 15;
    	int Y_MIDDLE = 55;
    	int Y_BOTTOM = 170;
    	int BTN_WEIGHT = 118;
    	int BTN_HEIGHT = 40;
    	
    	lb1.setBounds(X_LEFT, Y_TOP-2, 150, 30); // "公開資料URL: " 標籤
    	tf1.setBounds(X_ROW1, Y_TOP, 500, 30); // 對應 lb1 之輸入區
    	btn1.setBounds(X_LEFT, Y_MIDDLE, BTN_WEIGHT, BTN_HEIGHT); // "JSON 開爬!"按鈕 
    	btn2.setBounds(X_LEFT+135, Y_MIDDLE, BTN_WEIGHT+14, BTN_HEIGHT); // "查看JSON資訊"按鈕 
    	btn3.setBounds(X_LEFT+280, Y_MIDDLE, BTN_WEIGHT+14, BTN_HEIGHT); // "存入Mongo DB"按鈕
    	btn_visitLink.setBounds(X_LEFT+425, Y_MIDDLE, BTN_WEIGHT+60, BTN_HEIGHT); // "造訪公開JSON網頁"按鈕
    	lb2.setBounds(X_LEFT, Y_BOTTOM+30, 200, 40);
    	lb_isJsonSaved.setBounds(X_LEFT, Y_BOTTOM-70, 230, 40);
    	lb_isDBSaved.setBounds(X_LEFT, Y_BOTTOM-40, 230, 40);
    	lb_dataAmount.setBounds(X_LEFT, Y_BOTTOM-8, 200, 40);
    	lb_indexQuery1.setBounds(X_LEFT+140, Y_BOTTOM-8, 200, 40);
    	//tf_index.setBounds(X_LEFT+220, Y_BOTTOM-8, 30, 35);
    	cboBox.setBounds(X_LEFT+230, Y_BOTTOM+5, 55, 20);
    	lb_indexQuery2.setBounds(X_LEFT+300, Y_BOTTOM-8, 200, 40);
    	btn_indexSubmit.setBounds(X_LEFT+420, Y_BOTTOM-2, 65, 30);
    	btn_visitShopLink.setBounds(X_LEFT+500, Y_BOTTOM-2, 110, 30);
    	lb_status1.setBounds(X_LEFT+222, Y_BOTTOM-70, 200, 40);
    	lb_status2.setBounds(X_LEFT+215, Y_BOTTOM-40, 200, 40);
    	lb_status3.setBounds(X_LEFT+90, Y_BOTTOM-8, 200, 40);
    	lb_author.setBounds(X_LEFT+580, Y_BOTTOM+65, 200, 40);
    }
    
    private static void disableIndexInput() {
    	lb_indexQuery1.setEnabled(false);
    	lb_indexQuery2.setEnabled(false);
    	cboBox.setEnabled(false);
    	btn_indexSubmit.setEnabled(false);
    	btn_visitShopLink.setEnabled(false);
    }
    private static void enableIndexInput() {
    	lb_indexQuery1.setEnabled(true);
    	lb_indexQuery2.setEnabled(true);
    	cboBox.setEnabled(true);
    	btn_indexSubmit.setEnabled(true);
    	btn_visitShopLink.setEnabled(true);
    }
    
    private static void visitLink(String url_) throws URISyntaxException, IOException {
    	Desktop desktop = Desktop.getDesktop();   
		URI uri_ = new URI(url_); // 建立URI統一資源識別符號
		desktop.browse(uri_); // 使用預設瀏覽器開啟超連結
    }
}

class WindowHandler extends WindowAdapter {
	  JFrame f;
	  public WindowHandler(JFrame f) {this.f=f;}
	  public void windowClosing(WindowEvent e) {
		  int result=JOptionPane.showConfirmDialog(f,
				  "確定要結束程式嗎?",
				  "確認訊息",
		          JOptionPane.YES_NO_OPTION,
		          JOptionPane.WARNING_MESSAGE);
		  if (result==JOptionPane.YES_OPTION) {System.exit(0);}
	  } 
}
