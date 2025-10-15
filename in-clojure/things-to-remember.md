# Clojure Study Guide

## Variables and Bindings

```clojure
;; def creates a global var
(def x 42)
(def name "Alice")

;; let creates local bindings
(let [a 10
      b 20]
  (+ a b)) ; returns 30
```

## Data Types

```clojure
;; Numbers
42
3.14

;; Strings
"Hello World"

;; Keywords (like symbols)
:name
:age

;; Booleans
true
false
nil ; represents null/nothing
```

## Collections

```clojure
;; Lists (linked lists)
'(1 2 3 4)
(list 1 2 3 4)

;; Vectors (like arrays)
[1 2 3 4]
(vector 1 2 3 4)

;; Maps (key-value pairs)
{:name "Bob" :age 25}
(hash-map :name "Bob" :age 25)

;; Sets
#{1 2 3 4}
(hash-set 1 2 3 4)
```

## Functions

```clojure
;; Define a function
(defn greet [name]
  (str "Hello, " name "!"))

;; Call the function
(greet "World") ; returns "Hello, World!"

;; Anonymous function
(fn [x] (* x 2))

;; Short form for anonymous functions
#(* % 2) ; % is the argument
```

## Loops and Iteration

### loop/recur (explicit recursion)
```clojure
(loop [i 0
       acc []]
  (if (< i 5)
    (recur (inc i) (conj acc i))
    acc)) ; returns [0 1 2 3 4]
```

### for (list comprehension)
```clojure
;; Generate a sequence
(for [x (range 5)]
  (* x 2)) ; returns (0 2 4 6 8)

;; With conditions
(for [x (range 10)
      :when (even? x)]
  x) ; returns (0 2 4 6 8)
```

### doseq (side effects)
```clojure
;; For side effects (like printing)
(doseq [x (range 3)]
  (println x))
```

### map, filter, reduce
```clojure
;; map applies function to each element
(map inc [1 2 3 4]) ; returns (2 3 4 5)

;; filter keeps elements that match predicate
(filter even? [1 2 3 4 5 6]) ; returns (2 4 6)

;; reduce combines elements
(reduce + [1 2 3 4]) ; returns 10
```

## Conditionals

```clojure
;; if
(if (> 5 3)
  "yes"
  "no") ; returns "yes"

;; when (no else clause)
(when (> 5 3)
  (println "5 is greater than 3")
  "yes") ; returns "yes"

;; cond (multiple conditions)
(cond
  (< x 0) "negative"
  (= x 0) "zero"
  :else "positive") ; :else is the default
```

## Working with Collections

```clojure
;; Access elements
(first [1 2 3]) ; returns 1
(rest [1 2 3])  ; returns (2 3)
(nth [1 2 3] 1) ; returns 2 (0-indexed)

;; Add elements
(conj [1 2] 3)     ; returns [1 2 3]
(cons 0 [1 2 3])   ; returns (0 1 2 3)

;; Maps
(get {:a 1 :b 2} :a)     ; returns 1
(:a {:a 1 :b 2})         ; keyword as function
(assoc {:a 1} :b 2)      ; returns {:a 1 :b 2}
```

## Common Predicates

```clojure
(nil? x)      ; true if x is nil
(empty? coll) ; true if collection is empty
(even? n)     ; true if number is even
(odd? n)      ; true if number is odd
(pos? n)      ; true if number is positive
(neg? n)      ; true if number is negative
```

## String Operations

```clojure
(str "Hello" " " "World")     ; concatenation
(count "Hello")               ; length
(subs "Hello" 1 3)           ; substring "el"
```

## REPL Tips

```clojure
;; Start REPL and try:
(+ 1 2 3)
(def numbers [1 2 3 4 5])
(map #(* % %) numbers) ; square each number
```

## Key Concepts

- **Immutability**: Data structures don't change, operations return new ones
- **Prefix notation**: Function comes first `(+ 1 2)` not `1 + 2`
- **Parentheses**: Everything is a list, first element is the function
- **Lazy sequences**: Many operations return lazy sequences (computed on demand)