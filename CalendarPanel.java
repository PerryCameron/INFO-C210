import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CalendarPanel extends JPanel {
	//ImageIcon icon = new ImageIcon(imgURL); 
	
		
	private String[] daysOfWeek = {"Sun","Mon","Tue","Wed","Thu","Fri","Sat"};
	// private String[] monthsOfYear = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	int tailSet;
	private static final int GRAY = 1;
	private static final int LIGHTGRAY = 0;
	private static final int YELLOW = 2;
	private static final int RED = 3;

	
	public CalendarPanel (int month, int border, GregorianCalendar gCalendar) {
		JPanel tileContainer = new JPanel();
		JPanel headingPanel = new JPanel();
		
		
		JLabel monthLabel = new JLabel(SidePanel.monthList[month]);
		monthLabel.setForeground(new Theme(month).getTextColor());
		monthLabel.setHorizontalAlignment(JLabel.CENTER);
		
		headingPanel.setBackground(new Theme(month).getBackGroundColor());
		headingPanel.add(monthLabel);
		this.tailSet = 42 - (gCalendar.monthNumDays[month] + gCalendar.monthOffsets[month]); // figue up number of trailing tiles needed
		tileContainer.setLayout(new GridLayout(7,7));
		tileContainer.setBackground(new Theme(month).getBackGroundColor());
		
		if (border > 0) {
			tileContainer.setBorder(BorderFactory.createLineBorder(Color.GRAY, border));
		}
		setLayout(new BorderLayout());
		for(int i=0;i<7;i++) {         // creates panels for day headings
			tileContainer.add(new DayPanel(daysOfWeek[i],1,Color.LIGHT_GRAY,new Theme(month).getPanelColor()));
		}
		for(int i=0;i<gCalendar.monthOffsets[month];i++) {    // creates blank panels for spacers
			tileContainer.add(new DayPanel("",1,Color.LIGHT_GRAY,new Theme(month).getPanelColor()));
		}
		for(int i=1;i<gCalendar.monthNumDays[month]+1;i++) {  // creates a panel for each day
			if (gCalendar.currentDay == i && gCalendar.currentMonth == month+1 && gCalendar.currentYear == gCalendar.yearSelected) { 
			tileContainer.add(new DayPanel("" + i,1,Color.YELLOW,new Theme(month).getPanelColor()));
			} else if((gCalendar.monthOffsets[month] + i) % 7 ==0) {
			tileContainer.add(new DayPanel("" + i,1,Color.RED,new Theme(month).getPanelColor()));
			} else if((gCalendar.monthOffsets[month] + i - 1) % 7 ==0) {
			tileContainer.add(new DayPanel("" + i,1,Color.RED,new Theme(month).getPanelColor()));
			} else {
			tileContainer.add(new DayPanel("" + i,1,Color.LIGHT_GRAY,new Theme(month).getPanelColor()));
			}
			
		}
		for(int i = 0; i < tailSet; i++) {  // creates panels for any spaces left at the end
			tileContainer.add(new DayPanel("",0,new Theme(month).getPanelColor(),new Theme(month).getPanelColor()));
		}
		add(headingPanel, BorderLayout.PAGE_START);
		add(tileContainer, BorderLayout.CENTER);
	}
}

