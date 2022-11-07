package course.microkernel;

import course.microkernel.plugin_manager.DeliveryPluginManager;
import course.microkernel.presentation.Console;

import java.io.File;

public class Application {

    public static void main(String[] args) {

        //Inicializar el plugin manager con la ruta base de la aplicación.
        String basePath = getBaseFilePath();
        try {
            DeliveryPluginManager.init(basePath);

            Console presentationObject = new Console();
            presentationObject.start();

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Obtiene la ruta base donde está corriendo la aplicación, sin importar que sea desde un archivo .class
     * o desde un paquete .jar.
     * */
    private static String getBaseFilePath(){
        String path = Application.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        File pathFile = new File(path);
        if(pathFile.isFile()){
            path = pathFile.getParent();

            if(!path.endsWith(File.separator)) path += File.separator;

        }

        return path;
    }

}
