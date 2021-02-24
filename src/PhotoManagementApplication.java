/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photomanagementapplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import javax.swing.*;
import java.io.Serializable;
/**
 *
 * @author Hafiz Burhan
 */
public class PhotoManagementApplication implements Serializable 
{

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException 
    {
        // TODO code application logic here
        LinkedList<Album> albums = new LinkedList<>();
        PhotoManager photoManager = new PhotoManager();
        JFrame f=new JFrame();
        
        String [] photos = {"hedgehog.jpg", "wolf.jpg", "bear.jpg", "butterfly1.jpg", "butterfly2.jpg", "fox.jpg", "panda.jpg", "racoon.jpg"};
        
        LinkedList<String> tempTags = new LinkedList<>();
        tempTags.insert("animal");
        tempTags.insert("hedgehog");
        tempTags.insert("grass");
        tempTags.insert("apple");
        tempTags.insert("green");
        photoManager.addPhoto(new Photo("hedgehog.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("animal");
        tempTags.insert("bear");
        tempTags.insert("cub");
        tempTags.insert("grass");
        tempTags.insert("wind");
        photoManager.addPhoto(new Photo("bear.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("insect");
        tempTags.insert("butterfly");
        tempTags.insert("flower");
        tempTags.insert("color");
        photoManager.addPhoto(new Photo("butterfly1.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("insect");
        tempTags.insert("butterfly");
        tempTags.insert("flower");
        tempTags.insert("black");
        photoManager.addPhoto(new Photo("butterfly2.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("animal");
        tempTags.insert("fox");
        tempTags.insert("tree");
        tempTags.insert("forest");
        tempTags.insert("grass");
        photoManager.addPhoto(new Photo("fox.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("animal");
        tempTags.insert("bear");
        tempTags.insert("panda");
        tempTags.insert("grass");
        photoManager.addPhoto(new Photo("panda.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("animal");
        tempTags.insert("wolf");
        tempTags.insert("mountain");
        tempTags.insert("sky");
        tempTags.insert("snow");
        tempTags.insert("cloud");
        photoManager.addPhoto(new Photo("wolf.jpg", tempTags));
        
        tempTags = new LinkedList<>();
        tempTags.insert("animal");
        tempTags.insert("racoon");
        tempTags.insert("log");
        tempTags.insert("snow");
        photoManager.addPhoto(new Photo("racoon.jpg", tempTags));
        FileOutputStream fout=new FileOutputStream("./text.txt",true);
        int choice;
        do{
            Scanner sc = new Scanner(System.in);
            choice=Integer.parseInt(JOptionPane.showInputDialog(null,"\nMAIN MENU\n"
                    + "1. See Albums\n"
                    + "2. Create an Album\n"
                    + "3. See All Photos\n"
                    + "4. Exit","INPUT(MAIN MENU)",JOptionPane.INFORMATION_MESSAGE));
            switch(choice){
                case 1:
                    if(albums.empty()){
                       do
                       {
                        int ch=Integer.parseInt(JOptionPane.showInputDialog(null,"No Albums Created Yet!!!"
                                + "\n**********************\n"
                                + "1 ) Read From File \n"
                                + "2 ) Create Albem and Press Exits","Your Choice is IMPORTANT",JOptionPane.INFORMATION_MESSAGE));
                        switch(ch)
                        {
                            case 1:
                             
                               
                                   Scanner x;
                                   x =new Scanner(new File("./text.txt"));
                                   while(x.hasNext())
                                   {
                                       String al_name=x.next();
                                       String cond=x.nextLine();
                                       albums.insert(new Album(al_name, cond, photoManager));
                                   }
                                break;
                            case 2:
                                break;
                            default:
                                JOptionPane.showMessageDialog(f,"!!!! Error : Wrong Choice\nPlease Enter again","!!ERROR",JOptionPane.INFORMATION_MESSAGE);
                        }
                        if(ch==2 || ch==1)
                            break;
                       }while(true);
                    } 
                    else {
                        String temp=null;
                        albums.setCurrent(albums.getHead());
                        for(int i = 0; i < albums.getSize(); i++){
                            LinkedList<Photo> retrievedPhotos = albums.retrieve().getPhotos();
                            if(retrievedPhotos == null){
                               
                                for(String str : photos){
                                    temp+=str+"\n";
                                }
                                 JOptionPane.showMessageDialog(f,"\n"+ (i+1) +". ["+albums.retrieve().getName()+"] Photos Are:\n"+temp,"ALBUM AND PICTURE",JOptionPane.INFORMATION_MESSAGE);
                                break;
                            } 
                            temp=null;
                            LinkedList<Photo> tempList =  retrievedPhotos;
                            tempList.setCurrent(tempList.getHead());
                            for(int j = 0; j < tempList.getSize(); j++){
                                temp+=tempList.retrieve().getPath()+"\n";
                                tempList.findNext();
                            }
                            JOptionPane.showMessageDialog(f,"\n"+ (i+1) +". ["+albums.retrieve().getName()+"] Photos Are:\n"+temp,"ALBUM AND PICTURE",JOptionPane.INFORMATION_MESSAGE);
                            albums.findNext();
                        }
                    }
                    break;
                case 2:
                    sc = new Scanner(System.in);
                    String album_name;
                    album_name=JOptionPane.showInputDialog(null,"Enter the Album Name:","INPUT",JOptionPane.INFORMATION_MESSAGE);
                    album_name = album_name.toUpperCase();
                     String condition=null;
                    condition=JOptionPane.showInputDialog(null,"Enter tags CONDITION for Album:(If more then One Seperate by AND )\n"
                            + "animal, grass , green\n"
                            + "apple, wind , bear\n"
                            + "cub, hedgehog , insect\n"
                            + "butterfly, black , flower\n"
                            + "fox, tree , forest\n"
                            + "panda, wolf , mountain\n"
                            + "snow, cloud , raccoon , log\n","INPUT",JOptionPane.INFORMATION_MESSAGE);
                   
                    
                    albums.insert(new Album(album_name, condition, photoManager));
                    char ch=JOptionPane.showInputDialog(null,"Do you want to Save that Album wi2"
                            + "th the Following Tags on File\n"
                            + "Y for Saving on File\n"
                            + "N for Not Saving on File\n","PLEASE Choose ONE",JOptionPane.INFORMATION_MESSAGE).charAt(0);
                    
                    if(Character.toUpperCase(ch)=='Y')//writing on a File
                    {
                       String temp=null;
                       String input=album_name+"\t"+condition+"\n";
                       char character[]=input.toCharArray();
                       for(int i=0;i<input.length();i++)
                           fout.write(character[i]);
                    }
                    break;
                case 3:
                    String temp=null;
                    
                    for(String str : photos){
                       temp+=str+"\n";
                    }
                    JOptionPane.showMessageDialog(f,"\nAll Photos Are:\n"+temp,"ALL PHOTOS",JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
//           clearConsole();
        }while(true);
        
    }
    public final static void clearConsole()
    {
        try{
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")){
                Runtime.getRuntime().exec("cls");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e){
            //  Handle any exceptions.
        }
    }
    
}
