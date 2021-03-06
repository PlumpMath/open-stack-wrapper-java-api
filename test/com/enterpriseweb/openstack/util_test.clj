(ns com.enterpriseweb.openstack.util-test
  (:use [com.enterpriseweb.openstack.util])
  (:require [com.enterpriseweb.java-json.tools :as tools :only [clojure-json->java-json java-json->clojure-json]]
            [clojure.test :refer :all])
  (:import [org.json JSONObject]))


(deftest simple-test
  (testing json-with-url-to-delete-entity
    (is (let [b-url "http://my-url/"
              id "my-id"
              path "my-path/"
              base {:eps-url b-url :id id}
              result (assoc base :url (str b-url path id))]
          (=
           (->(json-with-url-to-delete-entity (tools/clojure-json->java-json base) path)
              tools/java-json->clojure-json)
           (-> (tools/clojure-json->java-json result)
               tools/java-json->clojure-json))))))
