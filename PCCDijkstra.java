import java.util.ArrayList;
import java.util.Collections;
public class PCCDijkstra implements IPCC{
  private ArrayList <Integer> SommetsMarques = new ArrayList<Integer>();
  private ArrayList<Integer>resultat = new ArrayList<Integer>();
  private IGraphe G;
  private ArrayList<Integer>Predecesseur = new ArrayList<Integer>();

  public PCCDijkstra(IGraphe G1){
    G = G1; 
  }

  private boolean EstMarques(int sommet){
    boolean verif = false;
    for(int a = 0; a < SommetsMarques.size(); ++a){
      if (sommet   == SommetsMarques.get(a)){
        return true;
      }
    }
    return verif;
  }       
 
  public ArrayList<Integer> Resultat(int SDepart,int Sfin , int distance) throws ValuationNegative, IllegalArgumentException{
    ArrayList<Integer>adjacents;
    ArrayList<Integer>adjacentsValeur = new ArrayList<Integer>();
    ArrayList<Integer> test = new ArrayList<Integer>();
    G.estOK(SDepart,Sfin);
    int Scourant = SDepart;
    for(int z = 0; z < G.getNbSommets();++z){
      Predecesseur.add(0);
    }

    //initialisation du tableau de resultat de disatances
    for(int i = 0; i < G.getNbSommets();++i){
      resultat.add(Integer.MAX_VALUE); 
    }

    if (SDepart != Sfin){
      resultat.remove(SDepart - 1) ;
      resultat.add(SDepart - 1, 0);
      
    }
    else{
      resultat.remove(SDepart - 1) ;
      int luimeme = 0;
      if (G.getValuation( SDepart,  Sfin) < 0){
        throw new ValuationNegative("le graphe contient des valuations négatives");
      }
      if(G.getValuation( SDepart,  Sfin) != Integer.MAX_VALUE){
        luimeme = G.getValuation( SDepart,  Sfin);
      }
      resultat.add(SDepart - 1, luimeme);
    }

    
    

    while(SommetsMarques.size() < G.getNbSommets() ){
      adjacents = G.getAdjacent(Scourant);
      adjacentsValeur.clear();
      
      
      for(int i = 0; i < adjacents.size();++i){
        if(G.getValuation( Scourant, adjacents.get(i)) < 0) {
          throw new ValuationNegative("le graphe contient des valuations négatives");
          
        }
        adjacentsValeur.add(G.getValuation( Scourant, adjacents.get(i) ) );
      }

      for(int i = 0; i < adjacents.size();++i){
        
        if(resultat.get(Scourant-1) + adjacentsValeur.get(i)    <   resultat.get(adjacents.get(i) - 1)){
          int tmp = resultat.get(Scourant-1) + adjacentsValeur.get(i);
          resultat.remove((adjacents.get(i) - 1));
          Predecesseur.remove(adjacents.get(i) - 1);
          Predecesseur.add(adjacents.get(i) - 1,Scourant);
          resultat.add((adjacents.get(i) - 1),tmp);
        }
      }

      //recherche du plus petit
      int min = Integer.MAX_VALUE; 
      int index = -2;
      //quand on veut retraiter un sommet
      for (int i = 0; i < G.getNbSommets();++i){
        
        if (!EstMarques(i + 1) && resultat.get(i) < min){
          min = resultat.get(i);
          index = i;
        }
      }
      
      SommetsMarques.add(index + 1);
      Scourant = index + 1;
      if (index == Sfin - 1){
        System.out.println("Dijkstra");
        System.out.println(""+ resultat.get(index));
        distance = resultat.get(index);
        
        //System.out.println("Le chemin le plus court pour aller de "+ SDepart+ " vers " + Sfin +" est de " + resultat.get(index));
       
        
        //System.out.print("Le chemin est ");
        int tmp = Sfin - 1 ;
        
        while(SDepart - 1 != tmp){
          
          test.add((Predecesseur.get(tmp)));   
          tmp = Predecesseur.get(tmp) - 1;
        }
        Collections.reverse(test);
        for (int b = 0 ; b < test.size(); ++b){
          System.out.print(test.get(b) + " ");
        }
        
        
        System.out.print(Sfin);       
        
       
        return test;
      }

    }
    return new ArrayList<Integer>();
  }
  
}