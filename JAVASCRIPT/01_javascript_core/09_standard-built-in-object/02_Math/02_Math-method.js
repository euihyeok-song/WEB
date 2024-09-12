
/* 절댓값 */
console.log(Math.abs(-10));                     
console.log(Math.abs('-10'));                   
console.log(Math.abs(''));                     
console.log(Math.abs([]));                      
console.log(Math.abs(null));                    
console.log(Math.abs(undefined));               
console.log(Math.abs({}));                      
console.log(Math.abs('math'));                 
console.log(Math.abs());                       
console.log('--------------------');

/* 반올림 */
console.log(Math.round(10.1));                  
console.log(Math.round(10.9));                  
console.log(Math.round(-10.1));                 
console.log(Math.round(-10.9));                 
console.log(Math.round(10));                    
console.log(Math.round());                      
console.log('--------------------');

/* 올림 */
console.log(Math.ceil(10.1));                 
console.log(Math.ceil(10.9));                 
console.log(Math.ceil(-10.1));                
console.log(Math.ceil(-10.9));                
console.log(Math.ceil(10));                   
console.log(Math.ceil());                     
console.log('--------------------');

/* 버림 */
console.log(Math.floor(10.1));                
console.log(Math.floor(10.9));                
console.log(Math.floor(-10.1));               
console.log(Math.floor(-10.9));               
console.log(Math.floor(10));                  
console.log(Math.floor());                    
console.log('--------------------');

/* 루트 */
console.log(Math.sqrt(4));                    
console.log(Math.sqrt(-4));                   
console.log(Math.sqrt(2));                    
console.log(Math.sqrt(1));                    
console.log(Math.sqrt(0));                    
console.log(Math.sqrt());                     
console.log('--------------------');

/* 난수 */
console.log(Math.random());               
const random = Math.floor((Math.random() * 100) + 1);
console.log(random);
console.log('--------------------');

/* 제곱근 */
console.log(Math.pow(2, 2));                 
console.log(Math.pow(2, -2));                
console.log(Math.pow(2));                    

/* 재곱근 */
console.log(2 ** 2);                         
console.log(2 ** -2);                        
console.log('--------------------');

/* Max 최대 */
console.log(Math.max(10));                   
console.log(Math.max(10, 20));               
console.log(Math.max(10, 20, 30));           
console.log(Math.max());                     
console.log('--------------------');          

/* Min 최소 */
console.log(Math.min(10));                   
console.log(Math.min(10, 20));               
console.log(Math.min(10, 20, 30));           
console.log(Math.min());                     
console.log('--------------------');      
