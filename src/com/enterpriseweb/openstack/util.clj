(ns com.enterpriseweb.openstack.util
  (:require [com.enterpriseweb.java-json.protocol :refer :all]))

(defn json-with-url-to-delete-entity
  "takes the 'first estructured endpoint url' and the 'id' from the java-json and assoc a 'url' value to this java-json"
  [json-java-object path]
  (let [first-url (get-in+ json-java-object [:eps-url])
        id (get-in+ json-java-object [:id])
        modified-json (assoc+ json-java-object :url (str first-url path id))]
    modified-json))
