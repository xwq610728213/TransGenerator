import java.io._
import util.Random.nextInt

object creatChain{
    def creatOneChain(depth: Int, min_branches: Int, max_branches: Int, is_tree: Boolean, group_num: Int, property: String, writer: PrintWriter, univ: Int, depart: Int) : Int = {
        if(depth == 0)
            return group_num + 1;
        var branches = min_branches + nextInt( max_branches - min_branches + 1);
        var max_group = group_num + 1;
        if(is_tree){
            //if we want to generate a structure in form of a tree
            for(x <- Range(0,branches)){

                writer.write("<http://www.Department" + depart + ".University" + univ + ".edu/ResearchGroup" + max_group + "> <" + property + "> <http://www.Department" + depart + ".University" + univ + ".edu/ResearchGroup" + group_num + "> .\n")
                max_group = creatOneChain(depth - 1, min_branches, max_branches, is_tree, max_group, property, writer, univ, depart);
            }
        }else{
            //if it is a chain with branches
            for(x <- Range(0,branches)){
                writer.write("<http://www.Department" + depart + ".University" + univ + ".edu/ResearchGroup" + (max_group + x) + "> <" + property + "> <http://www.Department" + depart + ".University" + univ + ".edu/ResearchGroup" + group_num + "> .\n")
            }
            max_group = creatOneChain(depth - 1, min_branches, max_branches, is_tree, max_group + branches - 1, property, writer, univ, depart);
        }
        return max_group;
    }

    val usage = "Usage: creatChain [--num-univ num] [--max-depart-num num] [--min-depart-num num] [--max-depth num] [--min-depth num] [--max-branches-num num] [--min-branches-num num] [--is-tree]"


    def main(args: Array[String]) {
        var min_depth = 3;
        var max_depth = 3;
        var depart_num_max = 1;
        var depart_num_min = 1;
        var num_univ = 1;
        var min_branches, max_branches = 1;
        var is_tree = false;
        var reppath = "";


        if(args.length == 0) println(usage);
        val arglist = args.toList;


        def nextOption(list: List[String]) : Unit = {
            list match {
                case Nil =>
                    return;
                case "--num-univ" :: value :: tail =>
                    num_univ = value.toInt; nextOption(tail);
                case "--max-depart-num" :: value :: tail =>
                    depart_num_max = value.toInt; nextOption(tail);
                case "--min-depart-num" :: value :: tail =>
                    depart_num_min = value.toInt; nextOption(tail);
                case "--min-depth" :: value :: tail =>
                    min_depth = value.toInt; nextOption(tail);
                case "--max-depth" :: value :: tail =>
                    max_depth = value.toInt; nextOption(tail);
                case "--min-branches-num" :: value :: tail =>
                    min_branches = value.toInt; nextOption(tail);
                case "--max-branches-num" :: value :: tail =>
                    max_branches = value.toInt; nextOption(tail);
                case "--is-tree" :: tail =>
                    is_tree = true; nextOption(tail);
                case "--path" :: value :: tail =>
                    reppath = value; nextOption(tail);
                case option :: tail =>
                    println(option + " is a unknown option!!"); println(usage); System.exit(0);
            }
            return;
        }

        nextOption(arglist);



        val writer = new PrintWriter(new File(reppath + "lubm" + num_univ + "_depart" + depart_num_min + "to" + depart_num_max + "_depth" + min_depth + "to" + max_depth + "_branches" + min_branches + "to" + max_branches + (if (is_tree) "_tree" else "") + ".nt"));

        for(univ <- Range(0, num_univ); depart <- Range(0, depart_num_min + nextInt(depart_num_max - depart_num_min + 1))){
            var depth = min_depth + nextInt(max_depth - min_depth + 1);
            var max_group = creatOneChain(depth, min_branches, max_branches, is_tree, 0, "http://swat.cse.lehigh.edu/onto/univ-bench.owl#subOrganizationOf", writer, univ, depart);
        }

        println("File created")
        println("Generate " + depart_num_min + " to " + depart_num_max + " departements for " + num_univ + " universities, each of these departements has a depth varie from " + min_depth + " to " + max_depth + " and a cardinality varie from " + min_branches + " to " + max_branches + ".")
        writer.close();
    }
}
