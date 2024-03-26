package com.example.application.GestionImages;

public class Gestion_chemins_Images {
    // Méthode pour convertir un chemin complet en chemin relatif
    public static String chemincomplet_cheminrelatif(String cheminComplet, String cheminBase) {
        if (cheminComplet.startsWith(cheminBase)) {
            return cheminComplet.substring(cheminBase.length());
        }
        return cheminComplet;
    }

        public static String Gestion_chemins_Images_logo = chemincomplet_cheminrelatif("C:\\Users\\jhsgu\\Desktop\\Application\\Application\\Images\\logo\\","C:\\Users\\jhsgu\\Desktop\\Application\\Application\\");
        public static String Gestion_chemins_Images_Face = chemincomplet_cheminrelatif("C:\\Users\\jhsgu\\Desktop\\Application\\Application\\Images\\Images_Client\\Face\\", "C:\\Users\\jhsgu\\Desktop\\Application\\Application\\");
        public static String Gestion_chemins_Images_Droite = chemincomplet_cheminrelatif("C:\\Users\\jhsgu\\Desktop\\Application\\Application\\Images\\Images_Client\\Profil_Droit\\", "C:\\Users\\jhsgu\\Desktop\\Application\\Application\\");
        public static String Gestion_chemins_Images_gauche = chemincomplet_cheminrelatif("C:\\Users\\jhsgu\\Desktop\\Application\\Application\\Images\\Images_Client\\Profil_Gauche\\", "C:\\Users\\jhsgu\\Desktop\\Application\\Application\\");
        public static String Gestion_chemins_Images_Derriere = chemincomplet_cheminrelatif("C:\\Users\\jhsgu\\Desktop\\Application\\Application\\Images\\Images_Client\\Derriere\\", "C:\\Users\\jhsgu\\Desktop\\Application\\Application\\");


}

