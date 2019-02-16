# creatChain
    Scala script for creating a LUBM-like assertion dataset with only transitive property

# Description
    This script will generate a DIY LUBM-like n-triple dataset which only contains the assertions of transitive property lubm:subOrganisationOf. Each individual is named by http://www.DepartmentY.UniversityX.edu/ResearchGroupZ of which X,Y and Z are numbers generated automatically.
    Some parameters can be set up while using the generator.
    **--num-univ** means the number of universities appears in the dataset, **--max-depart-num** and **--min-depart-num** limit the number of department appearing in each university. From these settings we can determine the number of hierarchy structures in the dataset. **--max-depth** and **--min-depth** give us a tolerance of depth in each hierarchy structure, **--max-branches-num** and **--max-branches-num** show how many branches will be given to each node. Intuitively, **--path** helps us set the output path of the dataset.
