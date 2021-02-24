package photomanagementapplication;

import Photo;
import PhotoManager;

public class Album 
{
    String name, condition;
    PhotoManager manager;
    LinkedList<String> tags;
    LinkedList<Photo> photos;
    // Constructor
    public Album(String name, String condition, PhotoManager manager){
        this.name = name;
        this.condition = condition;
        this.manager = manager;
        
        photos = new LinkedList<>();
        
        tags = new LinkedList<>();
        condition = condition.replaceAll("\\s+","");
        String [] arr = condition.split("AND");
        
        if(!condition.equals("")){
            for(String t : arr){
                tags.insert(t);
            }
        }
        
    }
    // Return the name of the album
    public String getName(){
        return name;
    }
    // Return the condition associated with the album
    public String getCondition(){
        return condition;
    }
    // Return the manager
    public PhotoManager getManager(){
        return manager;
    }
//     Return all photos that satisfy the album condition
    public LinkedList<Photo> getPhotos(){
        photos = new LinkedList<>();
        
        boolean illegalTag = false;
        
        if(tags.empty()) return manager.getAllPhotos();
        
        LinkedList<String> commonPhotos = new LinkedList<>();
        
        tags.findFirst();
        for(int i = 0; i < tags.getSize(); i++){
            if(!manager.getPhotos().findKey(tags.retrieve())){
                illegalTag = true;
            }
            tags.findNext();
        }
        if(illegalTag == false){
            if(tags.getSize() == 1){
                tags.findFirst();
                LinkedList<String> tempPhotos = manager.getPhotos().findAndReturnData(tags.retrieve()); 
                tempPhotos.findFirst();
                for(int i = 0; i < tempPhotos.getSize(); i++){
                    photos.insert(new Photo(tempPhotos.retrieve(), new LinkedList<>()));
                    tempPhotos.findNext();
                }
                return photos;
            }
            
            //make a list of lists (of photos with all tags no condition)
            LinkedList<LinkedList<String>> tempList = new LinkedList<>();
            tags.findFirst();
            for(int j = 0; j < tags.getSize(); j++){
                tempList.insert(manager.getPhotos().findAndReturnData(tags.retrieve()));
                tags.findNext();
            }

            //fill list1 and list2
            tempList.findFirst();
            LinkedList<String> list1 = tempList.retrieve();
            tempList.findNext();
            LinkedList<String> list2 = tempList.retrieve();

            commonPhotos = new LinkedList<>();

            list1.findFirst();

            //find common between list1 & list2
            //then add to commonPhotos list as initial common list
            for(int i = 0; i < list1.getSize(); i++){
                list2.findFirst();
                for(int j = 0; j < list2.getSize(); j++){
                    if((list1.retrieve()).equals(list2.retrieve())){
                        commonPhotos.insert(list1.retrieve());
                    }
                    list2.findNext();
                }
                list1.findNext();
            }
            //compare and find common between commonPhotos list and the list in tempList
            //then add (which are not common) to removablePhotos
            LinkedList<String> removablePhotos = new LinkedList<>();
            tempList.findFirst();
            commonPhotos.findFirst();
            for(int i = 0; i < commonPhotos.getSize(); i++){
                tempList.findFirst();
                for(int j = 0; j < tempList.getSize(); j++){
                    if(!tempList.retrieve().search(commonPhotos.retrieve())){
                        removablePhotos.insert(commonPhotos.retrieve());
                    }
                    tempList.findNext();
                }
                commonPhotos.findNext();
            }
            //Remove photos from commonPhotos, which are stored in removablePhotos list
            removablePhotos.findFirst();
            for(int i = 0; i < removablePhotos.getSize(); i++){
                commonPhotos.deleteNode(removablePhotos.retrieve());
                removablePhotos.findNext();
            }

            //Make LinkedList<Photo> of String photo names
            commonPhotos.findFirst();
            for(int i = 0; i < commonPhotos.getSize(); i++){
                photos.insert(new Photo(commonPhotos.retrieve(), new LinkedList<>()));
                commonPhotos.findNext();
            }
            
            
        }
        return photos;
    }
    // Return the number of tag comparisons used to find all photos of the album
    public int getNbComps(){
        return tags.getSize();
    }
}
