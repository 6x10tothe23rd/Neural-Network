package core;

import java.util.*;

//This class is called NetworkList
public class NetworkList<T> {
    public ArrayList<T> net = new ArrayList<T>();
    public ArrayList<Connection> conns = new ArrayList<Connection>();

    //There's another class here for some reason
    public class Connection {
        private T start;
        private T end;
        public double strength;

        //This constructor is downright basic
        public Connection(T start, T end, double strength){
            this.start = start;
            this.end = end;
            this.strength = strength;
        }

        //Probably could've overridden .equals()
        public boolean matches(Connection other){     
            return this.start.equals(other.start) && this.end.equals(other.end);
        }
        
        public boolean selfOnly(){
            return start.equals(end);
        }
    }

    //I feel like I named this function well enough to describe it
    private boolean checkExists(Connection conn){
        boolean found = false;
        for(Connection c : conns){
            if(conn.matches(c)){
                found = true;
                break;
            }
        }
        return found;
    }

    //There's one line in this one, how d*mb are you?
    public void resetConnections(){
        this.conns = new ArrayList<Connection>();
    }

    //This might actually be complicated enough to need a comment, it only makes the connection if it's not a direct
    //Loop and isn't already registered
    public boolean connect(T parent, T child, double strength){
        Connection newConn = new Connection(parent, child, strength);
        if(!checkExists(newConn) && !newConn.selfOnly()){
            conns.add(newConn);
            return true;
        }
        else {
            return false;
        }
    }

    //Default strength connection
    public boolean connect(T parent, T child){
        return connect(parent, child, 1);
    }

    //This function doesn't work on Batman
    public ArrayList<T> getParents(T member){
        ArrayList<T> result = new ArrayList<T>();

        for(Connection c : conns){
            if(c.end.equals(member)){
                result.add(c.start);
            }
        }

        return result;
    }

    //This function is easiest to run in China circa 1995
    public ArrayList<T> getChildren(T member){
        ArrayList<T> result = new ArrayList<T>();

        for(Connection c : conns){
            if(c.start.equals(member)){
                result.add(c.end);
            }
        }

        return result;
    }

    //Opposite function to Oprah
    public double getWeight(T start, T end){
        double weight = 0;

        Connection testConn = new Connection(start, end, 0);
        for(Connection c : conns){
            if(testConn.matches(c)){
                weight = c.strength;
                break;
            }
        }

        return weight;
    }
}
