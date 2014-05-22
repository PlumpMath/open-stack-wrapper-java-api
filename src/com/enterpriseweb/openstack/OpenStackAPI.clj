(ns com.enterpriseweb.openstack.OpenStackAPI
  (:require [com.enterpriseweb.java-json.protocol :refer :all]
            [com.enterpriseweb.java-json.util :refer :all]
            [com.enterpriseweb.java-json.tools :refer :all]
            [com.enterpriseweb.openstack.wrapper.core :as os-core]
            [com.enterpriseweb.openstack.wrapper.util :as util])
  (:gen-class :methods
              [#^{:static true} [makeCall [org.json.JSONObject] org.json.JSONObject]]))


                                        ;TODO throw exception if function is not evaluated!!
(defn mapping [option]
  (letfn  []

    (condp = option
      :tokens [ os-core/tokens nil :url :username :password]
      :tenants [os-core/tenants nil :token-id :url]
      :endpoints [os-core/endpoints-adaptated nil  :url :username :password :tenant-name]
      :list-images [os-core/service-call
                    (fn [j]
                      (assoc+ j :path "/images"))
                    :url :eps-token-id :path]
      :list-flavors [os-core/service-call
                     (fn [j]
                       (assoc+ j :path "/flavors"))
                     :url :eps-token-id :path]
      :list-networks [os-core/service-call
                      (fn [j]
                        (assoc+ j :path "v2.0/networks"))
                      :url :eps-token-id :path]
      :list-subnets [os-core/service-call
                     (fn [j]
                       (assoc+ j :path "v2.0/subnets"))
                     :url :eps-token-id :path]
      :delete-network [os-core/delete
                       (fn [j]
                         (json-url-delete-adapter-add-id j "v2.0/networks/"))
                       :url :eps-token-id]
      :delete-subnet [os-core/delete
                      (fn [j]
                        (json-url-delete-adapter-add-id j "v2.0/subnets/"))
                      :url :eps-token-id]
      :delete-server [os-core/delete
                      (fn [j]
                        (json-url-delete-adapter-add-id j "/servers/"))
                      :url :eps-token-id]
      :create-network [os-core/create-network
                       nil
                       :token-id :quantum-url :network-name]
      :create-subnet [os-core/create-subnet
                      nil
                      :token-id :quantum-url :network-id :cidr :start :end]
      :create-server [os-core/create-server
                      nil
                      :token-id :nova-url :server-name :flavor-href :image-href :network-id])))


(defn -makeCall [json-java-object]
  (let [[fn data-adapter-fn & more] (mapping (keyword (get-in+ json-java-object [:action])))]
    (if (nil? data-adapter-fn)
      (dispatch json-java-object fn more)
      (dispatch (data-adapter-fn json-java-object) fn more))))
