package photomanagementapplication;

public class PhotoManager {
	// Constructor
        LinkedList<Photo> photos;
        BST<LinkedList<Photo>> bst;
	public PhotoManager(){
            photos = new LinkedList<>();
            bst = new BST<>();
        }
	// Add a photo
	public void addPhoto(Photo p){
            photos.insert(new Photo(p.getPath(), new LinkedList<>()));
            
            LinkedList<String> tempTags = p.getTags();
            tempTags.setCurrent(tempTags.getHead());
            for(int i = 0; i < tempTags.getSize(); i++){
                if(bst.findKey(tempTags.retrieve())){
                    bst.findKeyAndInsertData(tempTags.retrieve(), p.getPath());
                } else {
                    LinkedList<String> temp = new LinkedList<>();
                    temp.insert(p.getPath());
                    bst.insert(tempTags.retrieve(), temp);
                    
                }
                tempTags.findNext();
            }
        }
	// Delete a photo
	public void deletePhoto(String photo){
            photos.deleteNode(new Photo(photo, new LinkedList<>()));
            bst.deleteFromBST(photo);
        }
	// Return the inverted index of all managed photos
	public BST<LinkedList<Photo>> getPhotos(){
            return bst;
        }
        public LinkedList<Photo> getAllPhotos(){
            return photos;
        }
}
