/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.wat4method.superherospringmvc.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sg.wat4method.superherospringmvc.model.Picture;


public class AlbumDaoInMemImpl implements AlbumDao {
    
    private Map<Integer, Picture> pictureMap = new HashMap<>();
    
    private int pictureCounter = 0;

    @Override
    public Picture addPicture(Picture picture) {
        picture.setPictureId(pictureCounter);
        pictureCounter++;
        pictureMap.put(picture.getPictureId(), picture);
        return picture;
    }

    @Override
    public void deletePicture(int pictureId) {
        pictureMap.remove(pictureId);
    }

    @Override
    public Picture getPictureById(int pictureId) {
        return pictureMap.get(pictureId);
    }

    @Override
    public List<Picture> getAllPictures() {
        List<Picture> pictureList = new ArrayList<>(pictureMap.values());
        return pictureList;
    }
}

