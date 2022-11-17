package model;

import java.util.ArrayList;
import java.util.Random;

public class Playlist {
    private ArrayList<Audio> audios;

    private String name;
    private String id;

    public Playlist(String name, String id) {
        audios = new ArrayList<Audio>();

        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**Description:
     * @param
     * @return
     */
    public boolean addSong(Audio newSong){
        audios.add(newSong);

        return true;
    }

    public boolean deleteSong(int deleteSong) {
        audios.remove(deleteSong);

        return true;
    }

    public String toString() {
        String message = "";

        message += "Nombre: " + name + " Id: " + id;

        return message;
    }

    public String audioToString() {
        String message = "";

        for (int i = 0; i<audios.size(); i++){
            message += (i+1)+". "+audios.get(i).getName()+"\n";
        }

        return message;
    }

    public String generateId() {
        Random random = new Random();

        String message = "";
        int matrix[][] = new int[6][6];

        if (audios.isEmpty()){
            message = "The playlist is empty";

        } else {

            for (int i = 0; i<matrix.length; i++) {
                for (int j = 0; j< matrix.length; j++) {
                    matrix[i][j] = random.nextInt(10);
                    message += matrix[i][j] + " ";

                }

                message += "\n";
            }

            switch (checkPlaylistContent()) {
                case 1 -> message += "Share Code: " + idOnlySongs(matrix);
                case 2 -> message += "Share Code: " + idOnlyPodcasts(matrix);
                case 3 -> message += "Share Code: " + idBothAudios(matrix);
            }

        }

        return message;
    }

    public int checkPlaylistContent(){
        boolean hasSongs = false;
        boolean hasPodcasts = false;

        for (int i = 0; i<audios.size() && (!hasPodcasts || !hasSongs); i++) {
            if (audios.get(i) instanceof Song) {
                hasSongs = true;
            }

            if (audios.get(i) instanceof Podcast) {
                hasPodcasts = true;
            }
        }

        if (hasSongs && hasPodcasts) {
            return 3;

        } else if(hasPodcasts) {
            return 2;

        } else {
            return 1;
        }
    }

    public String idOnlySongs(int[][] matrix){
        String message = "";

        for (int i = matrix.length-1; i >= 0; i--) {
            message += matrix[i][0];
        }

        for (int i = 1; i<matrix.length-1; i++) {
            message += matrix[i][i];
        }

        for (int i = matrix.length-1; i >= 0; i--) {
            message += matrix[i][5];
        }

        return message;
    }

    public String idOnlyPodcasts(int[][] matrix){
        String message = "";

        for (int i = 0; i<3; i++) {
            message += matrix[0][i];
        }

        for (int i = 1; i<matrix.length-1; i++) {
            message += matrix[i][2];
        }

        for (int i = 2; i > 4; i++) {
            message += matrix[5][i];
        }

        for (int i = matrix.length-2; i<=1; i++) {
            message += matrix[i][3];
        }

        for (int i = 3; i< matrix.length; i++) {
            message += matrix[0][i];
        }

        return message;
    }

    public String idBothAudios(int[][] matrix){
        String message = "";
        int count = 0;

        for (int i = matrix.length-1; i >= 0; i--) {

            if (i<= 2) {
                count++;
            }

            for (int j = matrix.length-1; j >= (0+count); j--) {
                if(i % 2 == 0) {
                    if (!(j % 2 == 0)) {
                        message += matrix[i][j];
                    }
                } else {
                    if (j % 2 == 0) {
                        message += matrix[i][j];
                    }
                }

            }
        }

        return message;
    }

}
