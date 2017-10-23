package com.decipherx.fintech.ElasticSearch;

import com.decipherx.fintech.ElasticSearch.service.RestService;
import com.decipherx.fintech.ElasticSearch.service.file.DownloadService;
import com.decipherx.fintech.ElasticSearch.service.search.ProcessParams;
import com.decipherx.fintech.ElasticSearch.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

import static java.lang.System.exit;

@SpringBootApplication
public class ElasticSearchApplication implements CommandLineRunner{

	@Autowired
	DownloadService downloadService;

	@Autowired
	RestService restService;

	public static void main(String[] args) throws Exception{
		SpringApplication.run(ElasticSearchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\n\n============= WELCOME TO ELASTIC SEARCH ============\n");
		System.out.println("Please select any of the options :");
		System.out.println("1. Please enter File Download URL ");
		System.out.println("2. Fetch data with parameters");
		System.out.println("3. Exit");
		System.out.print(">> ");

		Scanner scanner = new Scanner(System.in);
		char input= scanner.next().charAt(0);
		while (input!='3'){
			switch (input) {
				case '1':
					//  prompt for the user's name
					System.out.print("Please enter File Download URL: ");
					// get their input as a String
					String downloadUrl = scanner.next();
					downloadService.setFileDownloadUrl(downloadUrl);
					downloadService.downloadFile();
					downloadService.unzipDownloadedFile();
					break;

				case '2':
					// prompt for their age
					System.out.println("Fetch data with parameters: ");
					String none = scanner.nextLine();
					System.out.print("\t (a) Plan Name : ");
					String planName = scanner.nextLine();
					System.out.println("");

					System.out.print("\t (b) Sponsor Name : ");
					String sponsorName = scanner.nextLine();
					System.out.println("");

					System.out.print("\t (c) Sponsor State : ");
					String sponsorState = scanner.nextLine();
					System.out.println("");

					System.out.println(planName + "=>" + sponsorName + "=>" + sponsorState);

//					ProcessParams processParams = new ProcessParams("WEST ESSEX GRAPHICS, INC. 401(K) PLAN",
//							"WEST ESSEX GRAPHICS, INC." ,
//							"NJ");
					ProcessParams processParams = new ProcessParams(planName, sponsorName, sponsorState);
					restService.searchByCriteria(processParams.getDataString());

					break;

				case '3':
					exit(0);

				default:
					System.out.println("Please try again ! \n\n ");

			}
			System.out.println("Please select any of the options :");
			System.out.println("1. Please enter File Download URL ");
			System.out.println("2. Fetch data with parameters");
			System.out.println("3. Exit");
			System.out.print(">> ");
			input= scanner.next().charAt(0);
		}


	}
}
