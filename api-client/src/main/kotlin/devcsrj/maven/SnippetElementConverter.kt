/**
 * Copyright © 2018 Reijhanniel Jearl Campos (devcsrj@apache.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package devcsrj.maven

import org.jsoup.nodes.Element
import pl.droidsonroids.jspoon.ElementConverter
import pl.droidsonroids.jspoon.annotation.Selector

internal class SnippetElementConverter : ElementConverter<List<Snippet>> {

    override fun convert(node: Element?, selector: Selector): List<Snippet> {
        val elem = node?.selectFirst(selector.value) ?: return emptyList()

        // Under this element there are <textarea>s with id '$snippetType-a'
        val snippets = mutableListOf<Snippet>()
        for (type in Snippet.Type.values()) {
            val prefix = type.name.toLowerCase()
            val textarea = elem.selectFirst("#$prefix-a") ?: continue

            snippets.add(Snippet(type, textarea.`val`()))
        }
        return snippets.toList()
    }

}
