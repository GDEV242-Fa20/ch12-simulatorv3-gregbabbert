import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * This is a simple model for a mountain lion.
 * Mountain lions don't require as much food as mountain lions
 * because they're good at conserving their energy.
 * Mountain lions also don't have offspring as much
 * so their breeding probabbility is low. They also don't 
 * attack other predators like mountain lions, so they're both competing
 * for the same source of food.
 * 
 * @author Greg Babbert
 * @version 2020.11.16
 */
public class MountainLion extends Animal
{
    // Characteristics shared by all mountain lions (class variables).
    
    // The age at which a mountain lion can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a mountain lion can live.
    private static final int MAX_AGE = 150;
    // The likelihood of a mountain lion breeding.
    private static final double BREEDING_PROBABILITY = 0.0122;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // The food value of a single rabbit. In effect, this is the
    // number of steps a mountain lion can go before it has to eat again.
    private static final int RABBIT_FOOD_VALUE = 25;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();
    
    // Individual characteristics (instance fields).
    // The mountain lion's food level, which is increased by eating rabbits.
    private int foodLevel;

    /**
     * Create a mountain lion. A mountain lion can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param randomAge If true, the mountain lion will have random age and hunger level.
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public MountainLion(boolean randomAge, Field field, Location location)
    {
        super(field, location);
        if(randomAge) {
            int random = rand.nextInt(MAX_AGE);
            setAge(random);
            foodLevel = rand.nextInt(RABBIT_FOOD_VALUE);
        }
        else {
            setAge(0);
            foodLevel = RABBIT_FOOD_VALUE;
        }
    }
    
    /**
     * This is what the mountain lion does most of the time: it hunts for
     * rabbits. In the process, it might breed, die of hunger,
     * or die of old age.
     * @param field The field currently occupied.
     * @param newmountain lions A list to return newly born mountain lions.
     */
    public void act(List<Animal> newMountainLions)
    {
        incrementAge();
        incrementHunger();
        if(isAlive()) {
            giveBirth(newMountainLions);            
            // Move towards a source of food if found.
            Location newLocation = findFood();
            if(newLocation == null) { 
                // No food found - try to move to a free location.
                newLocation = getField().freeAdjacentLocation(getLocation());
            }
            // See if it was possible to move.
            if(newLocation != null) {
                setLocation(newLocation);
            }
            else {
                // Overcrowding.
                setDead();
            }
        }
    }
    
    /**
     * Make this mountain lion more hungry. This could result in the mountain lion's death.
     */
    private void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
    }
    
    /**
     * Look for rabbits adjacent to the current location.
     * Only the first live rabbit is eaten.
     * @return Where food was found, or null if it wasn't.
     */
    private Location findFood()
    {
        Field field = getField();
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        while(it.hasNext()) {
            Location where = it.next();
            Object animal = field.getObjectAt(where);
            if(animal instanceof Rabbit) {
                Rabbit rabbit = (Rabbit) animal;
                if(rabbit.isAlive()) { 
                    rabbit.setDead();
                    foodLevel = RABBIT_FOOD_VALUE;
                    return where;
                }
            }
        }
        return null;
    }
    
    /**
     * Check whether or not this mountain lion is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param newMountainLions A list to return newly born mountain lions.
     */
    private void giveBirth(List<Animal> newMountainLions)
    {
        // New mountain lions are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            MountainLion young = new MountainLion(false, field, loc);
            newMountainLions.add(young);
        }
    }

    /**
     * @return the age at which a mountain lion starts to breed.
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }
    
    /**
     * @return the mountain lion's maximum age.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }
    
    /**
     * @return a random number.
     */
    public Random getRand()
    {
        return rand;
    }
    
    /**
     * Return the breeding probability for this animal.
     * @return The breeding proability for this animal.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }
    
    /**
     * Return the maximum litter size for this animal.
     * @return The maximum litter size for this animal.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }
    
}
