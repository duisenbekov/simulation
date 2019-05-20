import java.util.Random;


public class GA {

    Population population = new Population();
    Individual fittest;
    Individual secondFittest;
    int generationCount = 0;

    public static void main(String[] args) {

        Random rn = new Random();

        GA example = new GA();

        
        example.population.initializePopulation(10);

        
        example.population.calculateFitness();

        System.out.println("Generation number: " + (example.generationCount + 1) + " Fittest number: " + example.population.fittest);

        
        while (example.population.fittest < 5) {
            ++example.generationCount;

            
            example.selection();

            
            example.crossover();

            
            if (rn.nextInt()%7 < 5) {
            	example.mutation();
            }

            
            example.addFittestOffspring();

            
            example.population.calculateFitness();

            System.out.println("Generation number: " + example.generationCount + " Fittest number: " + example.population.fittest);
        }

        System.out.println("\nBest choice found in generation number " + example.generationCount);

    }

    
    void selection() {

        
        fittest = population.getFittest();

        
        secondFittest = population.getSecondFittest();
    }

    
    void crossover() {
        Random rn = new Random();

        
        int crossOverPoint = rn.nextInt(population.individuals[0].geneLength);

        
        for (int i = 0; i < crossOverPoint; i++) {
            int temp = fittest.genes[i];
            fittest.genes[i] = secondFittest.genes[i];
            secondFittest.genes[i] = temp;

        }

    }

    
    void mutation() {
        Random rn = new Random();

        
        int mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        
        if (fittest.genes[mutationPoint] == 0) {
            fittest.genes[mutationPoint] = 1;
        } else {
            fittest.genes[mutationPoint] = 0;
        }

        mutationPoint = rn.nextInt(population.individuals[0].geneLength);

        if (secondFittest.genes[mutationPoint] == 0) {
            secondFittest.genes[mutationPoint] = 1;
        } else {
            secondFittest.genes[mutationPoint] = 0;
        }
    }

    
    Individual getFittestOffspring() {
        if (fittest.fitness > secondFittest.fitness) {
            return fittest;
        }
        return secondFittest;
    }


    
    void addFittestOffspring() {

        
        fittest.calcFitness();
        secondFittest.calcFitness();

        
        int leastFittestIndex = population.getLeastFittestIndex();

        
        population.individuals[leastFittestIndex] = getFittestOffspring();
    }

}






