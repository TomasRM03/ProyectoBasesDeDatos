package com.uncuyo.dbapp.backup;

/**
 *
 * @author tomas
 */
public class PostgresBackup {
    private String host = "localhost";
    private String port = "5432";
    private String database = "uncuyo";
    private String username = "postgres";
    private String password = "1234";
    private String outputPath = "backup.dump";

    public PostgresBackup() {
    }
    
    
    public void realizarBackup() {
        try {
            String[] command = new String[] {
                "pg_dump",
                "-h", host,
                "-p", port,
                "-U", username,
                "-d", database,
                "-Fc",
                "-f", outputPath
            };

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PGPASSWORD", password); 
            
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Respaldo de la base de datos realizado correctamente");
            } else {
                System.err.println("Error al realizar el respaldo de la base de datos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void importarBackup() {
        
        String output = "./backup.dump";
        try {
            String[] command = new String[] {
                "pg_restore",
                "-c",
                "-U", username,
                "-d", database,
                output
            };

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.environment().put("PGPASSWORD", password); 
            
            Process process = pb.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                System.out.println("Base de datos importada correctamente");
            } else {
                System.err.println("Error al realizar la importación de la base de datos");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
