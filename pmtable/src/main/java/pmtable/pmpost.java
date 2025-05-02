package pmtable;
   
	import java.io.File;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.nio.file.StandardCopyOption;
	import java.nio.file.attribute.FileTime;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.ResultSetMetaData;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.time.Instant;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.concurrent.Executors;
	import java.util.concurrent.ScheduledExecutorService;
	import java.util.concurrent.TimeUnit;

	public class pmpost 
	 {
	    private static final String SOURCE_FOLDER_PATH = "C:\\Users\\ADMIN\\Desktop\\PM-data";
	    private static final String DESTINATION_FOLDER_PATH = "C:\\Users\\ADMIN\\Desktop\\destinationFolder";
	    private static final String JDBC_URL = "jdbc:postgresql://localhost:5432/MBH_project";
	    private static final String USERNAME = "postgres";
	    private static final String PASSWORD = "admin123";

     public static void main(String[] args)
      {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(pmpost::runTask, 0, 15, TimeUnit.MINUTES);
      }
     
     private static void runTask() 
     {
        moveFiles();
        fetchAndPrintData();
     }

    private static void moveFiles() 
    {
        File sourceFolder = new File(SOURCE_FOLDER_PATH);
        File destinationFolder = new File(DESTINATION_FOLDER_PATH);

        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) 
        {
            System.err.println("Source folder does not exist or is not a directory: " + SOURCE_FOLDER_PATH);
            return;
        }

        if (!destinationFolder.exists()) 
        {
            boolean created = destinationFolder.mkdirs();
            if (!created)
            {
                System.err.println("Failed to create destination folder: " + DESTINATION_FOLDER_PATH);
                return;
            }
        }

        File[] files = sourceFolder.listFiles();
        if (files != null)
        {
            for (File file : files)
            {
                Path sourcePath = file.toPath();
       
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
                String formattedDateTime = now.format(formatter);

                String fileName = "Done" + formattedDateTime + "_" + file.getName();

                Path destinationPath = Paths.get(DESTINATION_FOLDER_PATH, fileName);
                try
                {
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("File moved successfully: " + fileName);

                    // Set the last modified time of the moved file
                    Files.setLastModifiedTime(destinationPath, FileTime.from(Instant.now()));
                    System.out.println("Last modified time set to: " + Instant.now());
                } 
                catch (Exception e) 
                {
                    System.err.println("An error occurred while moving the file: " + file.getName());
                    e.printStackTrace();
                }
            }
        }
    }

    private static void fetchAndPrintData() 
    {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD))
        {
            if (connection != null) 
            {
                System.out.println("Connected to the database!");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM public.\"PMTable_rawData\"");
                System.out.println("Rows from PMTable_rawData:");
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                               
                for (int i = 1; i <= columnCount; i++)
                {
                    System.out.print(metaData.getColumnName(i) + "\t");
                }

                System.out.println(); 
              
                while (resultSet.next())
                {
                    for (int i = 1; i <= columnCount; i++)
                    {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println(); 
                }
            } 
            else
            {
                System.err.println("Failed to connect to the database!");
            }
           }
          catch (SQLException e) 
        {
            System.err.println("Connection failed! Check output console");
            e.printStackTrace();
        }
    }
 }