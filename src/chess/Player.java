package chess;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;



public class Player implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String name;
	private Integer gamesplayed;
	private Integer gameswon;
	

	public Player(String name)
	{
		this.name = name.trim();

		gamesplayed = new Integer(0);
		gameswon = new Integer(0);
	}
	

	public String name()
	{
		return name;
	}
	

	public Integer gamesplayed()
	{
		return gamesplayed;
	}
	

	public Integer gameswon()
	{
		return gameswon;
	}
	

	public Integer winpercent()
	{
		return new Integer((gameswon*100)/gamesplayed);
	}
	

	public void updateGamesPlayed()
	{
		gamesplayed++;
	}
	

	public void updateGamesWon()
	{
		gameswon++;
	}
	
	
	public static ArrayList<Player> fetch_players()         
	{
		Player tempplayer;
		ObjectInputStream input = null;
		ArrayList<Player> players = new ArrayList<Player>();
		try
		{
			File infile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			input = new ObjectInputStream(new FileInputStream(infile));
			try
			{
				while(true)
				{
					tempplayer = (Player) input.readObject();
					players.add(tempplayer);
				}
			}
			catch(EOFException e)
			{
				input.close();
			}
		}
		catch (FileNotFoundException e)
		{
			players.clear();
			return players;
		}
		catch (IOException e)
		{
			e.printStackTrace();
			try {input.close();} catch (IOException e1) {}
			JOptionPane.showMessageDialog(null, "No se pueden leer los archivos del juego requeridos :'(");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "ArchivoS del juego dañados. Haga clic en Aceptar para continuar compilando nuevo archivo");
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		return players;
	}
	
	public void Update_Player()
	{
		ObjectInputStream input = null;
		ObjectOutputStream output = null;
		Player temp_player;
		File inputfile=null;
		File outputfile=null;
		try
		{
			inputfile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			outputfile = new File(System.getProperty("user.dir")+ File.separator + "tempfile.dat");
		} catch (SecurityException e)
		{
			JOptionPane.showMessageDialog(null, "Permiso de lectura-escritura denegado. El programa no se ejecutara");
			System.exit(0);
		} 
		boolean playerdonotexist;
		try
		{
			if(outputfile.exists()==false)
				outputfile.createNewFile();
			if(inputfile.exists()==false)
			{
					output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile,true));
					output.writeObject(this);
			}
			else
			{
				input = new ObjectInputStream(new FileInputStream(inputfile));
				output = new ObjectOutputStream(new FileOutputStream(outputfile));
				playerdonotexist=true;
				try
				{
				while(true)
				{
					temp_player = (Player)input.readObject();
					if (temp_player.name().equals(name()))
					{
						output.writeObject(this);
						playerdonotexist = false;
					}
					else
						output.writeObject(temp_player);
				}
				}
				catch(EOFException e){
					input.close();
				}
				if(playerdonotexist)
					output.writeObject(this);
			}
			inputfile.delete();
			output.close();
			File newf = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
			if(outputfile.renameTo(newf)==false)
				System.out.println("Cambio de nombre fallido.");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No se pueden leer o escribir los archivos necesarios. Presiona Ok para continuar...");
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Archivo de datos del juego dañados. Haga clic en Aceptar para continuar compilando nuevo archivo");
		}
		catch (Exception e)
		{
			
		}
	}
}
