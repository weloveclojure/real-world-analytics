# real-world-analytics

Welcome, this repository facilitates We Love Clojure's "lines of code" analysis of [Real World](https://github.com/gothinkster/realworld) example implementations.

It's still a work in progress.

## External Dependencies
- [cloc](https://github.com/AlDanial/cloc)

## Usage

### Clone the repositories
The real-world repositories will be cloned into `./repos/front-end` & `./repos/back-end`.

`clojure -X:run-init`

### Run the analytics
Analytics are run over all of the real-world repositories. The data is stored as a vector of maps in an edn file at `./data/real-world.edn`.

`clojure -X:run-analytics`

### Status

This project is a work-in-progress. More functions for analyzing the data may be added for use at the REPL. If you'd like to play with the data yourself, simply start a repl and run `(read-string (slurp "data/real-world.edn))` and you'll have a vector of maps of the following shape:

```clojure
{:category :front-end, 
 :url "https://github.com/gothinkster/react-redux-realworld-example-app", 
 :dir "repos/front-end/gothinkster.react-redux-realworld-example-app", 
 :analytics {"header" {"cloc_url" "github.com/AlDanial/cloc", 
                       "cloc_version" "1.88", 
                       "elapsed_seconds" 0.0196750164031982, 
                       "n_files" 41, 
                       "n_lines" 2480, 
                       "files_per_second" 2083.8610326818, 
                       "lines_per_second" 126048.179537826}, 
             "JavaScript" {"nFiles" 38, 
                           "blank" 285, 
                           "comment" 6, 
                           "code" 2050}, 
             "Markdown" {"nFiles" 1, 
                         "blank" 26, 
                         "comment" 0, 
                         "code" 48}, 
             "JSON" {"nFiles" 1, 
                     "blank" 0, 
                     "comment" 0, 
                     "code" 31}, 
             "HTML" {"nFiles" 1, 
                     "blank" 3, 
                     "comment" 16, 
                     "code" 15}, 
             "SUM" {"blank" 314, 
                    "comment" 22, 
                    "code" 2144, 
                    "nFiles" 41}}}
```

The `:analytics` key is coupled to cloc at the moment, so simply see cloc documentation for its output structure, though it's pretty self-explanatory. 

It should now be easy for you to analyze this data with the full power of Clojure!

## License

Copyright © 2021 We Love Clojure

Distributed under the Eclipse Public License version 1.0.
