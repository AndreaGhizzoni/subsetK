package it.disi.unitn.subsetk;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Vector;

public class Launcher {

    static void subsetK(Integer[] A, boolean[] S, Integer k, Integer i ){
        Integer n = S.length;
        HashSet<Boolean> choices = new HashSet<>();
        if( i<n ){
            choices.add(true);
            choices.add(false);
        }

        for( boolean choice: choices ){
            S[i] = choice;
            if( sumUp(A, S).equals(k) ){
                addSolution(A, S);
            }
            subsetK(A, S, k, i+1 );
        }
    }

    static Integer sumUp( Integer[] A, boolean[] S ){
        Integer sum = 0;
        for( int i=0; i<A.length; i++)
            sum+=( S[i] ? A[i] : 0 );
        return sum;
    }

    static void addSolution( Integer[] A, boolean[] S ){
        Vector<Integer> sol = new Vector<>();
        for( int i=0; i<A.length; i++ )
            if( S[i] ) sol.add(A[i]);
        computedSolutions.add( sol );
    }

    static HashSet<Vector<Integer>> computedSolutions = new HashSet<>();

    public static void main(String...args){
        Integer[] A = {3, 5, 1, 7, 2, 6, 3, 2};
        boolean[] S = new boolean[A.length];
        Integer k = 8;

        System.out.println("Given the following array:");
        System.out.println(Arrays.toString(A));
        System.out.printf("All subsets that sum-up as %d are:\n",k);
        subsetK(A, S, k, 0);
        computedSolutions.forEach(
            System.out::println
        );
    }
}
