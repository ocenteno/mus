import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
void etiquetar(String version, String url, String credenciales){
      if(version == null || version == ""){
          version = "1.0"
      }else{
          version = version.replace("\n", "") + ".1"
      }
      sh "git tag $version"
      withCredentials([usernamePassword(credentialsId: credenciales, 
        passwordVariable: 'MI_PASSWORD', usernameVariable: 'MI_USUARIO')]) {
            // Como mi password tiene caracteres no compatibles con una URL
            // tengo que escapar esos caracteres
            MI_PASSWORD = URLEncoder.encode(MI_PASSWORD, "UTF-8")
            // Añado usurio y password a la url
            // Necesito httpss://usr:password@github....
            url = url.replace("https://", "https://$MI_USUARIO:$MI_PASSWORD@")
          // Voy a subir la etiqueta al GitHub
          sh "git push $url --tags"
      }
}
