package spaceExplorer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class HighScore {
    import java.io.BufferedReader;
    import java.io.File;
    import java.io.FileNotFoundException;
    import java.io.FileReader;
    import java.io.FileWriter;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.ArrayList;
    import java.util.List;

   

        public static void main(String[] args) throws IOException {
            // Format = 'Scheduler_Intesity_Bound'
            List<Run> runs = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                String[] names = args[i].split("_");
                runs.add(readCSVFile(args[i], names[1], names[2], names[3]));
            }
            toFile(runs);
        }

        /** @param filePath
         * @param intensity
         * @param bound
         * @param scheduler
         * @return Run object with averaged values to be put in a CSV file.
         * @throws FileNotFoundException */
        public static Run readCSVFile(String filePath, String intensity, String bound, String scheduler)
                throws FileNotFoundException {
            
            Run[] result = new Run[3];
            // Scan in from files.
            String line;

            // First line is variable names. (i -> -1)
            // Second line is data. (i -> 0 )
            int i = -2;
            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

                while ((line = br.readLine()) != null) {
                    // Sets to 0 upon taking data.
                    i++;
                    // use comma as separator
                    String[] data = line.split(",");
                    for (int k = 0; k < data.length; k++) {
                        System.out.println("have data: " + data[k]);
                    }
                    // Catch the first line of Strings
                    try {
                        Run t = new Run(intensity, bound, scheduler, Double.parseDouble(data[0]),
                                Double.parseDouble(data[1]), Double.parseDouble(data[2]),
                                Double.parseDouble(data[3]), Double.parseDouble(data[4]),
                                Double.parseDouble(data[5]));
                        result[i] = t;
                    } catch (NumberFormatException n) {
                        continue;
                    }
                }

            } catch (IOException e) {
                throw new RuntimeException("Encountered Error reading file " + filePath);
            }
            Run averaged = new Run(intensity, bound, scheduler, 
                average(result[0].wallTime,           result[1].wallTime,           result[2].wallTime), 
                average(result[0].kernelTime,         result[1].kernelTime,         result[2].kernelTime), 
                average(result[0].userTime,           result[1].userTime,           result[2].userTime), 
                average(result[0].cpuUsage,           result[1].cpuUsage,           result[2].cpuUsage), 
                average(result[0].involContextSwitch, result[1].involContextSwitch, result[2].involContextSwitch), 
                average(result[0].volContextSwitch,   result[1].involContextSwitch, result[2].involContextSwitch));
            
            return averaged;
        }

        /** Takes the average of any number of doubles.
         * 
         * @param nums
         * @return average of numbers */
        public static double average(double... nums) {
            double sum = 0;
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
            return sum / nums.length;
        }

        /** @param runs
         * @param completeFilePath */
        public static void toFile(List<Run> runs) {
            String path = "./AveragedAggregatedResults.csv";
            File data = new File(path);
            try {
                if (data.createNewFile()) {
                    System.out.println("Successfully created file: " + path);
                }
            } catch (IOException e) {
                throw new RuntimeException("Error creating the file: " + path);
            }
            PrintWriter pw1 = null;
            try {
                pw1 = new PrintWriter(new FileWriter(new File(path)));
            } catch (IOException e) {
                throw new RuntimeException("Error writing to file: " + path);
            }
            pw1.print("processIntesity,runTimeBound,scheduler,wallTime,"
                    + "kernelTime,userTime,cpuUsage,involContextSwitch,volContextSwitch\n");
            for (int i = 0; i < runs.size(); i++) {
                Run current = runs.get(i);
                pw1.print(current.processIntesity);
                pw1.print(",");
                pw1.print(current.runTimeBound);
                pw1.print(",");
                pw1.print(current.scheduler);
                pw1.print(",");
                pw1.print(current.wallTime);
                pw1.print(",");
                pw1.print(current.kernelTime);
                pw1.print(",");
                pw1.print(current.userTime);
                pw1.print(",");
                pw1.print(current.cpuUsage);
                pw1.print(",");
                pw1.print(current.involContextSwitch);
                pw1.print(",");
                pw1.print(current.volContextSwitch);
                pw1.print("\n");
            }
            pw1.close();
        }
    

}
