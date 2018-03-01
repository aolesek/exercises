package core;

import java.util.Scanner;

public class Application {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String objective;
		System.out.println("Podaj funkcj? celu ['2*x1 + 3*x2^3 + x3 max']");
		objective = in.nextLine();
		LinProgProblem problem = new LinProgProblem(objective);
		
		String limit;
		do {
			System.out.println();
			System.out.println("Dodaj ograniczenie: ['2*x1 + x2^2 < 11'; e aby zakonczyc]");
			limit = in.nextLine();
			
			if ( !(limit.equals("e")) ) {
				problem.addLimitation(new Limit(limit));
			}
		} while ( !(limit.equals("e")) );
		
		problem.defineStandardLowerLimits();
		
		String upperLimit;
		do {
			System.out.println();
			System.out.println("Jakie g?rne granice zastosowac dla zmiennych? [d - standardowe, s - jedna granica dla wszystkich zmiennych, a - osobne granice dla kazdej zmiennej");
			upperLimit = in.nextLine();
			
			if ( upperLimit.equals("d") ) {
				problem.defineStandardUpperLimits();
				
			} else if ( upperLimit.equals("s") ) {
				System.out.println("Podaj jedn? g?rn? granic? dla wszystkich zmiennych:");
				Double lim = Double.parseDouble( in.nextLine() );
				problem.defineCustomUpperLimits(lim);
				
			} else if ( upperLimit.equals("a") ) {
				Double[] limits = new Double[problem.findNumberOfVariables()];
				for(int i = 1; i < problem.findNumberOfVariables(); i++) {
					System.out.println("Podaj g?rn? granic? dla zmiennej x"+i);
					limits[i-1] = Double.parseDouble(in.nextLine());
				}
				problem.defineCustomUpperLimits(limits);
			}
			
		} while ( !(upperLimit.equals("d") || upperLimit.equals("s") || upperLimit.equals("a")) );
		
		System.out.println("Wszystkie ograniczenia:");
		System.out.println(problem);
		
		System.out.println("Ile punkt?w wylosowa? w ka?dej iteracji?");
		problem.setNumberOfPoints(Integer.parseInt(in.nextLine()));
		
		System.out.println("Ile iteracji wykona??");
		Integer iter = Integer.parseInt(in.nextLine());
		
		System.out.println("Rozmiar prostopad?o?cianu do poszukiwania s?siad?w.");
		problem.setSecondLoopOffset((Double.parseDouble(in.nextLine())));
		
		System.out.println("Wy?wietli? informacje o znalezionych punktach? [t/n]");
		String info = in.nextLine();
		
		if (info.equals("t"))
			problem.setInfo(true);
		
		

		System.out.println("#################   Losowanie punkt?w...");
		long start=System.currentTimeMillis();
		Point neighbor = problem.findGreatestPoint(problem.findFirstLoopPoints());
		
		System.out.println("\nNajwi?kszy punkt wylosowany w pierwszej iteracji");
		System.out.println(neighbor+"\n");
		Point point = problem.findGreatestPoint(
				problem.findSecondLoopPoints(neighbor.getCoordinates())
			);
		for (int i = 0; i < iter; i++) {
			point = problem.findGreatestPoint(
					problem.findSecondLoopPoints(point.getCoordinates())
					);
		}
		
		long stop=System.currentTimeMillis();
		
		System.out.println("\nNajwi?kszy punkt wylosowany ostatniej iteracji");
		System.out.println(point);
		
		
		System.out.println("Czas wykonania:"+(stop-start)/1000+"s");
	}

}
