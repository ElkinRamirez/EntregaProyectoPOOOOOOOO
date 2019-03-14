package chess;

import java.awt.*;
import javax.swing.*;

import pieces.*;


public class Cell extends JPanel implements Cloneable{
	

	private static final long serialVersionUID = 1L;
	private boolean ispossibledestination;
	private JLabel content;
	private Piece piece;
	int x,y;                             
	private boolean isSelected=false;
	private boolean ischeck=false;
	
	
	public Cell(int x,int y,Piece p)
	{		
		this.x=x;
		this.y=y;
		
		setLayout(new BorderLayout());
	
	 if((x+y)%2==0)
	  setBackground(Color.white);
	
	 else
	  setBackground(new Color(113,198,113));
	 
	 if(p!=null)
		 setPiece(p);
	}
	
	
	public Cell(Cell cell) throws CloneNotSupportedException
	{
		this.x=cell.x;
		this.y=cell.y;
		setLayout(new BorderLayout());
		if((x+y)%2==0)
			setBackground(Color.white);
		else
			setBackground(new Color(113,198,113));
		if(cell.getpiece()!=null)
		{
			setPiece(cell.getpiece().getcopy());
		}
		else
			piece=null;
	}
	
	public void setPiece(Piece p)    
	{
		piece=p;
		ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource(p.getPath()));
		content=new JLabel(img);
		this.add(content);
	}
	
	public void removePiece()      
	{
		if (piece instanceof King)
		{
			piece=null;
			this.remove(content);
		}
		else
		{
			piece=null;
			this.remove(content);
		}
	}
	
	
	public Piece getpiece()    
	{
		return this.piece;
	}
	
	public void select()       
	{
		this.setBorder(BorderFactory.createLineBorder(Color.red,6));
		this.isSelected=true;
	}
	
	public boolean isselected()
	{
		return this.isSelected;
	}
	
	public void deselect()     
	{
		this.setBorder(null);
		this.isSelected=false;
	}
	
	public void setpossibledestination()
	{
		this.setBorder(BorderFactory.createLineBorder(Color.blue,4));
		this.ispossibledestination=true;
	}
	
	public void removepossibledestination()
	{
		this.setBorder(null);
		this.ispossibledestination=false;
	}
	
	public boolean ispossibledestination() 
	{
		return this.ispossibledestination;
	}
	
	public void setcheck()     
	{
		this.setBackground(Color.RED);
		this.ischeck=true;
	}
	
	public void removecheck()  
	{
		this.setBorder(null);
		if((x+y)%2==0)
			setBackground(new Color(113,198,113));
		else
			setBackground(Color.white);
		this.ischeck=false;
	}
	
	public boolean ischeck()   
	{
		return ischeck;
	}
}