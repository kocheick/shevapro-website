package com.shevapro.website.styles

import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextDecorationLine
import com.varabyte.kobweb.compose.css.backgroundColor
import com.varabyte.kobweb.compose.css.boxShadow
import com.varabyte.kobweb.compose.css.fontSize
import com.varabyte.kobweb.compose.css.margin
import com.varabyte.kobweb.compose.css.overflowX
import com.varabyte.kobweb.compose.css.textDecorationLine
import com.varabyte.kobweb.silk.style.CssStyle
import com.varabyte.kobweb.silk.style.breakpoint.Breakpoint
import org.jetbrains.compose.web.css.*

object MarkdownStyles {

    val markdownContentCSS = """
        .markdown-content {
            font-family: Roboto, serif;
            line-height: 1.6;
            color: #9eacd5;
            max-width: 100%;
            background-color: #1a1b26;
            border-radius: 4px;
            padding: 0.7rem;
        }
        
        .markdown-content > h1, 
        .markdown-content > h2, 
        .markdown-content > h3, 
        .markdown-content > h4, 
        .markdown-content > h5 {
            color: #bb9af7;
            font-weight: bold;
            margin-top: 1rem;
            margin-bottom: 1rem;
        }
        
        .markdown-content > h1 {
            font-size: 2.5rem;
        }
        
        .markdown-content > h2 {
            font-size: 2.25rem;
        }
        
        .markdown-content > h3 {
            font-size: 1.75rem;
        }
        
        .markdown-content > h4 {
            font-size: 1.5rem;
        }
        
        .markdown-content > h5 {
            font-size: 1.25rem;
        }
        
        .markdown-content > p {
            font-size: 1rem;
            color: #9eacd5;
            margin-bottom: 1.25rem;
            font-weight: 400;
        }
        
        .markdown-content > ul, 
        .markdown-content > ol {
            margin: 1rem 0;
            padding-left: 1rem;
            color: #9eacd5;
            list-style-position: outside;
        }
        
        .markdown-content > ul {
            list-style-type: circle;
        }
        
        .markdown-content > ol {
            list-style-type: decimal;
        }
        
        .markdown-content li {
            margin-bottom: 0.5rem;
            line-height: 1.6;
        }
        
        .markdown-content a {
            color: indianred;
            text-decoration: none;
            font-weight: 600;
        }
        
        .markdown-content a:hover {
            text-decoration: underline;
        }
        
        .markdown-content blockquote,
        .markdown-content pre {
            display: block;
            border-radius: 12px;
            color: #d5d6e0;
            font-size: small;
            background-color: rgba(139, 0, 0, 0.5);
            margin: 0.3rem;
            padding: 0.5rem;
            box-shadow: 0 1px 3px 0 rgb(0 0 0 / 0.1), 0 1px 2px -1px rgb(0 0 0 / 0.1);
            white-space: pre-wrap;
        }
        
        .markdown-content > p code {
            background-color: #d2c2ae;
            color: #900;
            padding: 0.05rem;
            border-radius: 4px;
            font-family: 'JetBrains Mono', 'Fira Code', 'Monaco', 'Consolas', monospace;
            font-weight: 500;
        }
        
        .markdown-content pre {
            overflow-x: auto;
            font-family: 'JetBrains Mono', 'Fira Code', 'Monaco', 'Consolas', monospace;
        }
        
        .markdown-content pre code {
            background-color: transparent;
            color: inherit;
            padding: 0;
        }
        
        .markdown-content img {
            border-radius: 4px;
            max-width: 100%;
            height: auto;
            margin: 1.5rem 0;
        }
        
        .markdown-content table {
            width: 100%;
            border-collapse: collapse;
            margin: 1.5rem 0;
            background-color: #1a1b26;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05);
        }
        
        .markdown-content th {
            background-color: rgba(139, 0, 0, 0.3);
            color: #bb9af7;
            font-weight: 600;
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid rgba(139, 0, 0, 0.2);
        }
        
        .markdown-content td {
            padding: 1rem;
            border-bottom: 1px solid rgba(139, 0, 0, 0.2);
            color: #9eacd5;
        }
        
        .markdown-content tr:hover {
            background-color: rgba(139, 0, 0, 0.1);
        }
        
        .markdown-content hr {
            border: none;
            height: 1px;
            background-color: rgba(139, 0, 0, 0.3);
            margin: 1rem 0;
        }
        
        .markdown-content strong {
            font-weight: bold;
            color: #bb9af7;
        }
        
        .markdown-content em {
            font-style: italic;
            color: #9eacd5;
        }
        
        .markdown-content .screenshots-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: space-around;
            align-content: center;
        }
        
        .markdown-content .screenshots-container > img {
            width: 30%;
            padding: 0.5rem;
            border-radius: 4px;
        }
        
        @media (min-width: 475px) {
            .markdown-content img {
                margin: auto;
                width: 80%;
            }
        }
        
        @media (max-width: 768px) {
            .markdown-content .screenshots-container > img {
                width: 50%;
            }
        }
        
        @media (max-width: 640px) {
            .markdown-content .screenshots-container > img {
                width: 80%;
            }
        }
        
        @media (max-width: 475px) {
            .markdown-content .screenshots-container > img {
                width: 100%;
            }
            
            .markdown-content > h1 {
                font-size: 2rem;
            }
            
            .markdown-content > h2 {
                font-size: 1.75rem;
            }
            
            .markdown-content > h3 {
                font-size: 1.5rem;
            }
        }
    """.trimIndent()
}