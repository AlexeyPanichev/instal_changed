import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    private static final StringBuilder log = new StringBuilder();

    public static void main(String[] args) {
        String fileDirectory = "C:/Games";
        File gamesDirectory = new File(fileDirectory);
        if (!gamesDirectory.exists()) {
            if (!gamesDirectory.mkdir()) {
                log.append("Упс! Не получилось создать директорий: ").append(fileDirectory).append("\n");
                printLog();
                return;
            }
        }

        String[] directoriesToCreate = {
                "src",
                "src/main",
                "src/test",
                "res",
                "res/drawables",
                "res/vectors",
                "res/icons",
                "savegames",
                "temp"
        };

        String[] filesToCreate = {
                "src/main/Main.java",
                "src/main/Utils.java",
                "temp/temp.txt"
        };

        for (String dir : directoriesToCreate) {
            File directory = new File(fileDirectory, dir);
            if (!directory.exists()) {
                if (!directory.mkdir()) {
                    log.append("Ой-ой! Не получилось создать директорий: ").append(dir).append("\n");
                } else {
                    log.append("Директорий создан: ").append(dir).append("\n");
                }
            } else {
                log.append("Упс! Кажется такой директорий уже есть: ").append(dir).append("\n");
            }
            printLog();
        }

        for (String file : filesToCreate) {
            File newFile = new File(fileDirectory, file);
            if (!newFile.exists()) {
                try {
                    if (newFile.createNewFile()) {
                        log.append("Упс! Не получилось создать файл: ").append(file).append("\n");
                    }
                } catch (IOException e) {
                    log.append("ой-ой! Ошибочка вышла в создании файла: ").append(file).append("\n");
                    e.printStackTrace();
                }
            } else {
                log.append("Ой, кажется файл уже существует: ").append(file).append("\n");
            }
            printLog();
        }

        Path tempFilePath = Paths.get(fileDirectory, "temp/temp.txt");
        try (FileWriter fileWriter = new FileWriter(tempFilePath.toFile())) {
            fileWriter.write(log.toString());
            log.setLength(0);
            System.out.println("Файл создан: temp/temp.txt");
        } catch (IOException e) {
            log.append("Упс! Не получилось создать файл: temp/temp.txt").append("\n");
            e.printStackTrace();
        }
    }

    private static void printLog() {
        System.out.print(log);
        try {
            Files.write(Paths.get("C:/Games/temp/temp.txt"), log.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.setLength(0);
    }
}