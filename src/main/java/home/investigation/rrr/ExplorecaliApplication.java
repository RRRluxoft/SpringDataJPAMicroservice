package home.investigation.rrr;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.investigation.rrr.domain.Difficulty;
import home.investigation.rrr.domain.Region;
import home.investigation.rrr.services.TourPackageServiceImpl;
import home.investigation.rrr.services.TourServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class ExplorecaliApplication implements CommandLineRunner {

    @Autowired
    private TourServiceImpl tourService;

    @Autowired
    private TourPackageServiceImpl tourPackageService;


    public static void main(String[] args) {
        SpringApplication.run(ExplorecaliApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        //Create the default tour packages
        tourPackageService.createTourPackage("BC", "Backpack Cal");
        tourPackageService.createTourPackage("CC", "California Calm");
        tourPackageService.createTourPackage("CH", "California Hot springs");
        tourPackageService.createTourPackage("CY", "Cycle California");
        tourPackageService.createTourPackage("DS", "From Desert to Sea");
        tourPackageService.createTourPackage("KC", "Kids California");
        tourPackageService.createTourPackage("NW", "Nature Watch");
        tourPackageService.createTourPackage("SC", "Snowboard Cali");
        tourPackageService.createTourPackage("TC", "Taste of California");

        tourPackageService.lookup().forEach(System.out::println);
        System.out.println(String.format("Number of tours packages = %d", tourPackageService.total()));

        TourFromFile.importTours().forEach(t -> tourService.createTour(
            t.title, t.description, t.blurb, Integer.parseInt(t.price),
            t.length, t.bullets, t.keywords, t.packageType,
            Difficulty.valueOf(t.difficulty), Region.findByLabel(t.region)
        ));
        System.out.println(String.format("Number of tours %d", tourService.total()));
    }

    /**
     * Helper class to import the records in the ExploreCalifornia.json
     */
    static class TourFromFile {
        //attributes as listed in the .json file
        private String packageType, title, description, blurb, price, length, bullets, keywords, difficulty, region;

        /**
         * Open the ExploreCalifornia.json, unmarshal every entry into a TourFromFile Object.
         *
         * @return a List of TourFromFile objects.
         * @throws IOException if ObjectMapper unable to open file.
         */
        static List<TourFromFile> importTours() throws IOException {
            return new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).
                readValue(TourFromFile.class.getResourceAsStream("/ExploreCalifornia.json"), new TypeReference<List<TourFromFile>>() {
                });
        }
    }
}
