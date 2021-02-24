/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package photomanagementapplication;

import java.util.Scanner;
/**
 *
 * @author Hafiz Burhan
 */
public class PhotoManagementApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LinkedList<Album> albums = new LinkedList<>();
        PhotoManager photoManager = new PhotoManager();
        
        
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
    
        
        int choice;
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("\nMAIN MENU\n"
                    + "1. See Albums\n"
                    + "2. Create an Album\n"
                    + "3. See All Photos\n"
                    + "4. Exit");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    if(albums.empty()){
                        System.out.println("No Albums Created Yet!");
                    } else {
                        
                        albums.setCurrent(albums.getHead());
                        for(int i = 0; i < albums.getSize(); i++){
                            LinkedList<Photo> retrievedPhotos = albums.retrieve().getPhotos();
                            if(retrievedPhotos == null){
                                System.out.println("\n"+ (i+1) +". ["+albums.retrieve().getName()+"] Photos Are:");
                                for(String str : photos){
                                    System.out.print(str+", ");
                                }
                                System.out.println();
                                break;
                            } 
                            
                            LinkedList<Photo> tempList =  retrievedPhotos;
                            tempList.setCurrent(tempList.getHead());
                            System.out.println("\n"+ (i+1) +". ["+albums.retrieve().getName()+"] Photos Are:");
                            for(int j = 0; j < tempList.getSize(); j++){
                                System.out.print(tempList.retrieve().getPath()+", ");
                                tempList.findNext();
                            System.out.println();
                            }
                            albums.findNext();
                        }
                    }
                    break;
                case 2:
                    sc = new Scanner(System.in);
                    System.out.println("Enter the Album Name:");
                    String album_name = sc.nextLine();
                    album_name = album_name.toUpperCase();
                    System.out.println("Enter tags CONDITION for Album:(e.g animal AND green)");
                    String condition = sc.nextLine();
                    
                    albums.insert(new Album(album_name, condition, photoManager));
                    break;
                case 3:
                    System.out.println("\nAll Photos Are:");
                    for(String str : photos){
                        System.out.print(str+", ");
                    }
                    System.out.println();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
            clearConsole();
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
